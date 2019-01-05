package com.jackstraw.jhttp.base.api.service;


import com.jackstraw.jackhttp.annotation.Api;
import com.jackstraw.jackhttp.annotation.ParameterName;
import com.jackstraw.jackhttp.annotation.PathVariable;
import com.jackstraw.jackhttp.base.HttpMethod;
import com.jackstraw.jackhttp.help.NewApiUtil;
import com.jackstraw.jackhttp.okhttp3.ApiCallBack;
import com.jackstraw.jhttp.base.api.ApiConstant;
import com.jackstraw.jhttp.base.api.request.UserRequest;

/**
 * 用户相关接口
 */
public interface UserApiService {

    /**
     * 登录(psot方式)
     */
    @Api(value = NewApiUtil.KEY_BOOK_PARAMETER + ApiConstant.LOGIN)
    void userLogin(UserRequest request, ApiCallBack<Object> callBack);


    /**
     * 注册(get方式)
     */
    @Api(value = NewApiUtil.KEY_BOOK_PARAMETER + ApiConstant.LOGIN, method = HttpMethod.GET)
    void userRegister(UserRequest request, ApiCallBack<Object> callBack);


    /**
     * 获取用户信息的GET方式(ParameterName方式) (http://test1.ffrj.net/snsApi?do=login&userId=23107845&token=fddffdf)
     */
    @Api(value = NewApiUtil.KEY_BOOK_PARAMETER + ApiConstant.LOGIN, method = HttpMethod.GET)
    void userLoginGetParameterName(@ParameterName("userId") int userId, @ParameterName("token") String token, ApiCallBack<Object> callBack);

    /**
     * 获取用户信息的GET方式(PathVariable)  (http://test1.ffrj.net/snsApi?do=login&userId=23107845&token=fddffdf)
     */
    @Api(value = NewApiUtil.KEY_BOOK_PARAMETER + ApiConstant.LOGIN + "&userId={userId}&token={token}", method = HttpMethod.GET)
    void userLoginGetPathVariable(@PathVariable("userId") int userId, @PathVariable("token") String token, ApiCallBack<Object> callBack);

}
