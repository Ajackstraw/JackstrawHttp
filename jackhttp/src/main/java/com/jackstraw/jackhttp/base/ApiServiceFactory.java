package com.jackstraw.jackhttp.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.jackstraw.jackhttp.annotation.Api;
import com.jackstraw.jackhttp.annotation.ParameterName;
import com.jackstraw.jackhttp.annotation.PathVariable;
import com.jackstraw.jackhttp.help.AnnotationUtil;
import com.jackstraw.jackhttp.help.NewApiUtil;
import com.jackstraw.jackhttp.okhttp3.ApiCallBack;
import com.jackstraw.jackhttp.okhttp3.HttpCallback;
import com.jackstraw.jackhttp.okhttp3.OkHttpUtils;
import com.jackstraw.jackhttp.request.RequestInterface;
import com.jackstraw.jackhttp.response.BaseResponseBody;
import com.jackstraw.jackhttp.response.ResponseInterface;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * api代理
 */
public class ApiServiceFactory implements ServiceFactory {

    private static final String TAG = ApiServiceFactory.class.getSimpleName();

    @Override
    public <T> T createService(final Context ctx, Class<T> clazz) {
        InvocationHandler invocationHandler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                try {
                    //解析api注解
                    Api api = method.getAnnotation(Api.class);
                    if (api == null) {
                        Log.d(TAG, method.toString() + " 没有声明为@Api，忽略此方法.");
                        return null;
                    }
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 2 && api.method() != HttpMethod.GET && api.method() != HttpMethod.POST) {
                        throw new Exception("Api声明的方法，当method=" + api.method() + "时必须有两个参数，第二个参数类型必须是RequestCallBack");
                    }

                    //获取方法参数
                    Object entity = null;
                    ApiCallBack callback = null;
                    if (parameterTypes.length == 2) {
                        //只有一个参数
                        entity = args[0];
                        callback = (ApiCallBack<?>) args[args.length - 1];
                    }

                    //获取callback泛型的type
                    Type t = callback.getClass().getGenericSuperclass();
                    ParameterizedType pt = (ParameterizedType) t;
                    final Class resultType = (Class) pt.getActualTypeArguments()[0];

                    //获取api
                    String uri = api.value();
                    //判断相对路径与绝对路径
                    if (uri.startsWith("http") || uri.startsWith("https")) {
                        //此时视为绝对路径

                    } else {
                        if (!uri.startsWith(NewApiUtil.KEY_BOOK_API_URL)) {
                            //此时视为相对路径
                            uri = NewApiUtil.KEY_BOOK_API_URL + uri;
                        }
                    }

                    // 使用参数对象的属性设置参数
                    uri = AnnotationUtil.setParameters(uri, entity);

                    // 使用PathVariable、ParameterName注解设置参数
                    Annotation[][] annosArray = method.getParameterAnnotations();
                    Map<String, Object> pathValues = new HashMap<String, Object>();
                    Map<String, Object> parameterValues = new HashMap<String, Object>();
                    for (int i = 0; i < annosArray.length; i++) {
                        Annotation[] annos = annosArray[i];
                        for (int j = 0; j < annos.length; j++) {
                            Annotation anno = annos[j];
                            if (anno instanceof PathVariable) {
                                PathVariable pv = (PathVariable) anno;
                                String paramName = pv.value();
                                Object value = args[i];
                                pathValues.put(paramName, value);
                            }
                            if (anno instanceof ParameterName) {
                                ParameterName pn = (ParameterName) anno;
                                String paramName = pn.value();
                                Object value = args[i];
                                parameterValues.put(paramName, value);
                            }
                        }
                    }

                    //构建请求URL
                    uri = AnnotationUtil.setParameters(uri, pathValues);
                    uri = AnnotationUtil.addQueryString(uri, parameterValues);

                    //请求方法
                    final HttpMethod httpMethod = api.method();
                    String msg = String.format("Call api uri=<%s>, methodName=<%s>, parameters=<%s>", uri, method.getName(), Arrays.toString(args));
                    Log.d(TAG, msg);

                    //构建请求体
                    Request request = null;
                    RequestInterface requestInterface = OkHttpUtils.getRequestInterface();
                    if (httpMethod == HttpMethod.POST) {
                        //api名称（"?do="后面的字符串）
                        String apiName = uri.substring(uri.lastIndexOf(NewApiUtil.KEY_BOOK_PARAMETER) + NewApiUtil.KEY_BOOK_PARAMETER.length());
                        if(requestInterface != null){
                            request = requestInterface.createPostRequest(uri, apiName, JSON.toJSONString(entity));
                        }
                    } else if (httpMethod == HttpMethod.GET) {
                        if(requestInterface != null){
                            request = requestInterface.creatGetRequest(uri);
                        }
                    }

                    //执行请求任务
                    loadApi(ctx, request, resultType, callback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        //构造代理
        final Object instance = Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, invocationHandler);
        return (T) instance;
    }

    /**
     * 请求api
     *
     * @param context
     * @param request
     * @param resultType
     * @param callback
     */
    private void loadApi(final Context context, Request request, final Class resultType, final ApiCallBack callback) {
        OkHttpUtils.postAync(request, new HttpCallback() {

            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                ResponseInterface responseInterface = OkHttpUtils.getResponseInterface();
                if(responseInterface == null){
                    callback.onFail("", "Application中未初始化回调");
                }
                BaseResponseBody baseResponseBody = responseInterface.resultResponseBody(result);
                if(baseResponseBody == null){
                    callback.onFail("", "Application中回调方法处理欠缺");
                }

                String code = baseResponseBody.getCode();
                String message = baseResponseBody.getMessage();
                String data = baseResponseBody.getData();

                if(TextUtils.isEmpty(data)){
                    callback.onFail(code, message);
                }else {
                    callback.onSuccess(JSON.parseObject(data, resultType));
                }
                callback.onCompleted();
            }

            @Override
            public void onFailure(int code, String message) {
                super.onFailure(code, message);
                callback.onFail(code + "", message);
                callback.onCompleted();
            }
        });
    }
}
