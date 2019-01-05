package com.jackstraw.jhttp.base.api;

/**
 * @author pink-jackstraw
 * @date 2019/01/05
 * @describe
 */
public class ErrorCode {

    //数据库错误
    public final static int CREATE_OK = 111;
    public final static int CREATE_ERROR = 110;
    public final static int CREATE_HAD = 113;
    public final static int DELETE_OK = 121;
    public final static int DELETE_ERROR = 120;
    public final static int UPDATE_OK = 131;
    public final static int UPDATE_ERROR = 130;
    public final static int SELECT_OK = 141;
    public final static int SELECT_ERROR = 140;
    public final static int SELECT_EMPTY = 142;
    public final static int SELECT_FORMAT_ERR = 143;

    //参数错误
    public final static int PARAMS_ERROR = 210;
    public final static int PARAMS_EMPTY = 212;

    //操作错误
    public final static int LOGIN_ERROR = 311;
    public final static int LOGIN_OK = 310;
    public final static int LOGIN_EMPTY = 312;
    public final static int SIGNIN_ERROR = 321;
    public final static int SIGNIN_OK = 320;
    public final static int SIGNIN_EMPTY = 322;
    public final static int SIGNIN_HAD = 323;

    //数据处理
    public final static int SERIA_ERROR = 401;
    public final static int RESP_ERR = 411;

    //文件处理
    public final static int FILE_UPDATE_OK = 501;
    public final static int FILE_UPDATE_ERR = 500;
    public final static int FILE_DEL_OK = 511;
    public final static int FILE_DEL_ERR = 510;
    public final static int FILE_SELECT_ERR = 520;

    //第三方服务错误
    public final static int SERVER_DISCON = 603;
    public final static int SERVER_EMPTY = 602;
    public final static int SERVER_OK = 601;
    public final static int SERVER_ERROR = 600;
}
