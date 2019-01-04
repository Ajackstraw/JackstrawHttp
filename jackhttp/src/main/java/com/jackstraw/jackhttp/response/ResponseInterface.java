package com.jackstraw.jackhttp.response;

/**
 * @author pink-jackstraw
 * @date 2019/01/04
 * @describe
 */
public interface ResponseInterface {

    /**
     * 解析网络数据
     * @param result
     * @return
     */
    BaseResponseBody resultResponseBody(String result);

}
