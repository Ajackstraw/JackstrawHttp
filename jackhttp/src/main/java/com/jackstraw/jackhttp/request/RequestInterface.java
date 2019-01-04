package com.jackstraw.jackhttp.request;

import okhttp3.Request;

/**
 * @author pink-jackstraw
 * @date 2019/01/04
 * @describe
 */
public interface RequestInterface {

    /**
     * 构建 post 请求的 request
     * @param uri
     * @param apiName
     * @param requestModel
     * @return
     */
    Request createPostRequest(String uri, String apiName, String requestModel);

    /**
     * 构建 get 请求的 request
     * @param uri
     * @return
     */
    Request creatGetRequest(String uri);

}
