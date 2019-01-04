package com.jackstraw.jackhttp.okhttp3;

import java.lang.reflect.Type;

/**
 * 扩展的注解模式回调类，支持业务异常的回调函数
 *
 * @param <T>
 */
public abstract class ApiCallBack<T> {

    public abstract void onFail(String errorCode, String errorMsg);

    public abstract void onSuccess(T t);

    public void onCompleted() {}
}
