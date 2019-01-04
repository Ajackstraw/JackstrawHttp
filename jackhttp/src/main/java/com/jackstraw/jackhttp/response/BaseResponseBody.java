package com.jackstraw.jackhttp.response;

import java.io.Serializable;

/**
 * @author pink-jackstraw
 * @date 2019/1/1
 * @describe
 */
public class BaseResponseBody implements Serializable {

    //请求结果
    private String data;
    //错误码
    private String code;
    //错误信息
    private String message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
