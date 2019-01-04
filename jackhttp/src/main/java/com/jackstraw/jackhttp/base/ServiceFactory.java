package com.jackstraw.jackhttp.base;

import android.content.Context;

/**
 * @author pink-jackstraw
 * @date 2019/1/1
 * @describe 代理工厂
 */
public interface ServiceFactory {
	<T> T createService(Context ctx, Class<T> clazz);
}
