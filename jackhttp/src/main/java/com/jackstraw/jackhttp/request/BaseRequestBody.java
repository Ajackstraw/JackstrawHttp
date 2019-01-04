package com.jackstraw.jackhttp.request;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author pink-jackstraw
 * @date 2019/1/1
 * @describe 网络请求request基类(所有的网络请求必须继承这个类)
 */
public class BaseRequestBody implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
