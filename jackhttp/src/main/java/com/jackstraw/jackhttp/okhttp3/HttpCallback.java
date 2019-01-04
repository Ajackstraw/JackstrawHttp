package com.jackstraw.jackhttp.okhttp3;

/**
 * @author pink-jackstraw
 * @date 2019/1/1
 * @describe 自定义请求回调类
 */
public abstract class HttpCallback {

    /**
     * @param result  返回数据
     * @Description 请求成功时回调
     */
    public void onSuccess(String result) {}

    /**
     * @param code    状态码
     * @param message 状态消息
     * @Description 请求失败时回调
     */
    public void onFailure(int code, String message) {}

    /**
     * @param currentLen      进度
     * @param totalLen        总量
     * @Description 上传或下载时进度回调
     */
    public void onProgress(long currentLen, long totalLen) {}

    /**
     * 成功失败都会回调
     */
    public void onCompleted() {}
}
