package com.jackstraw.jackhttp.okhttp3;

import java.lang.reflect.Type;

/**
 * 扩展的注解模式回调类，支持业务异常的回调函数
 *
 * @param <T>
 */
public abstract class ApiCallBack<T> {

    /**
     * 请求失败
     *
     * @param isCommonError 是否为系统级的服务器错误code
     * @param errorCode
     * @param errorMsg
     */
    public abstract void onFail(boolean isCommonError, String errorCode, String errorMsg);

    public abstract void onSuccess(T t);

    public void onCompleted() {
    }

    public Type getGenericType() {
        return getClass().getGenericSuperclass();
    }
}
