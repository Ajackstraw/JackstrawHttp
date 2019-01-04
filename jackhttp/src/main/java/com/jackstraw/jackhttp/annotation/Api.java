package com.jackstraw.jackhttp.annotation;

import com.jackstraw.jackhttp.base.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author pink-jackstraw
 * @date 2019/1/1
 * @describe 声明api接口的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Api {
    /**
     * 接口的uri地址
     *
     * @return
     */
    String value();

    /**
     * 接口的方法，默认为POST
     *
     * @return
     */
    HttpMethod method() default HttpMethod.POST;

}
