package com.jackstraw.jhttp.base.api.request;

import com.alibaba.fastjson.annotation.JSONType;
import com.jackstraw.jackhttp.request.BaseRequestBody;

/**
 * @author pink-jackstraw
 * @date 2019/1/1
 * @describe 用户信息网络请求request基类
 */
@JSONType(orders = {"name", "password"})
public class UserRequest extends BaseRequestBody {

    //
    private String name;
    //
    private String password;

    public UserRequest() {
    }

    public UserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
