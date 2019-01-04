package com.jackstraw.jackhttp.base;

/**
 * @author pink-jackstraw
 * @date 2019/1/1
 * @describe 注解模式网络请求方式
 */
public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    HEAD("HEAD"),
    MOVE("MOVE"),
    COPY("COPY"),
    DELETE("DELETE"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE"),
    CONNECT("CONNECT");

    private final String value;

    private HttpMethod(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}