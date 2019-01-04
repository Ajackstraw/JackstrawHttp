package com.jackstraw.jackhttp.base;

import android.content.Context;


/**
 * 服务工具类
 *
 * @author Li Jian
 */
public class ApiServiceUtil {

    /**
     * 创建一个服务的实例
     *
     * @param ctx
     * @param clazz 必须是一个interface，里面的方法使用@Api注解
     * @return
     */
    public static <T> T getService(final Context ctx, Class<T> clazz) {
        ServiceFactory factory = new ApiServiceFactory();
        T t = factory.createService(ctx, clazz);
        return t;
    }
}
