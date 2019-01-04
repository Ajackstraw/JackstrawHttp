package com.jackstraw.jackhttp.help;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pink-jackstraw
 * @date 2019/1/1
 * @describe 注解解析
 */
public class AnnotationUtil {

    /**
     * 排除null
     *
     * @param value
     * @return
     */
    public static String getValidString(String value) {
        if (!"".equals(value)) {
            return value;
        }
        return "";
    }

    /**
     * 从String中读取出用{}包含的参数名
     *
     * @param src
     * @return
     */
    public static List<String> getParameterNames(String src) {
        int begin = 0, end = 0;
        List<String> result = new ArrayList<String>();
        while (begin >= 0 && end >= 0) {
            begin = src.indexOf('{', begin);
            if (begin < 0)
                break;
            end = src.indexOf('}', begin);
            if (end < 0)
                break;
            String key = src.substring(begin + 1, end);
            result.add(key);
            begin = end;
        }
        return result;
    }

    /**
     * 为String设置参数，参数名由{}包围，参数值从parameterObj的属性中获取
     *
     * @param src
     * @param parameterObj
     * @return
     */
    public static String setParameters(final String src, Object parameterObj) {
        if (parameterObj == null) {
            return src;
        }
        List<String> names = getParameterNames(src);
        if (names.isEmpty()) {
            return src;
        }

        String result = src;
        Class<?> clazz = parameterObj.getClass();
        for (String name : names) {
            // 获取getter方法的名称
            char[] ca = name.toCharArray();
            ca[0] = Character.toUpperCase(ca[0]);
            String getterName = "get" + new String(ca);
            try {
                Method m = clazz.getMethod(getterName);
                Object value = m.invoke(parameterObj);
                if (value == null) {
                    result = result.replace('{' + name + '}', "");
                } else {
                    result = result.replace('{' + name + '}', value.toString());
                }
            } catch (NoSuchMethodException e) {
                // 如果getter方法不存在，就不做处理
                // 因为有些参数根据PathVariable处理，不存在对应的getter方法
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String setParameters(final String src,
                                       Map<String, Object> values) {
        if (values == null || values.isEmpty()) {
            return src;
        }
        List<String> names = getParameterNames(src);
        if (names.isEmpty()) {
            return src;
        }

        String result = src;
        for (String name : names) {
            if (values.containsKey(name)) {
                Object value = values.get(name);
                if (value == null) {
                    result = result.replace('{' + name + '}', "");
                } else {
                    result = result.replace('{' + name + '}', value.toString());
                }
            }
        }
        return result;
    }

    public static String addQueryString(String url, Map<String, Object> values) {
        if (values == null || values.isEmpty())
            return url;
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            if (url.indexOf('?') < 0) {
                url += '?' + key + '=' + value;
            } else {
                url += '&' + key + '=' + value;
            }
        }
        return url;
    }
}
