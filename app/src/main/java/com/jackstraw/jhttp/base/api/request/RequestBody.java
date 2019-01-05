package com.jackstraw.jhttp.base.api.request;

import com.jackstraw.jackhttp.request.RequestInterface;

import okhttp3.Request;

/**
 * @author pink-jackstraw
 * @date 2019/01/04
 * @describe
 */
public class RequestBody implements RequestInterface {

    @Override
    public Request createPostRequest(String uri, String apiName, String requestModel) {

        return null;
    }

    @Override
    public Request creatGetRequest(String uri) {
        return null;
    }
}
