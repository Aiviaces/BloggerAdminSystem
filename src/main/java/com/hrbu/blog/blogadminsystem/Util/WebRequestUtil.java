package com.hrbu.blog.blogadminsystem.Util;

import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.WebParamIllegalException;

import javax.servlet.http.HttpServletRequest;

public class WebRequestUtil {

    // 判断字符串是否为空
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // 获取请求参数编码
    public static String getRequestEncoding(HttpServletRequest request) {
        return request.getCharacterEncoding();
    }

    // 获取请求参数源 URL
    public static String getRequestSourceURL(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    // 其他需要的方法...

    // 获取请求的参数值
    public static String getRequestParameterString(HttpServletRequest request, String paramName, boolean allow_null_or_empty) throws WebParamIllegalException {
        String param = request.getParameter(paramName);
        if (!allow_null_or_empty && isNullOrEmpty(param)) {
            throw new WebParamIllegalException(paramName);
        }
        return param;
    }

    public static int getRequestParameterInt(HttpServletRequest request, String paramName, boolean allow_null_or_empty) throws WebParamIllegalException {
        if (allow_null_or_empty) {
            return 0;
        } else {
            return Integer.parseInt(getRequestParameterString(request, paramName, false));
        }
    }

    /**
     * 设置请求会话attr
     * <p>
     * 目前已记录属性:
     * 1. user 用户对象
     * 2. user-pgroup-map 用户权限组map对象
     * 3. msg 返回前台的消息
     * 4. userNum 用户数量
     * 5. permissionGroupNum 用户组数量
     *
     * @param req        请求
     * @param key        键
     * @param value      值
     * @param allow_null 允许为空
     */
    public static void setRequestSessionAttr(HttpServletRequest req, String key, Object value, boolean allow_null) {
        if (!allow_null && (isNullOrEmpty(key) || value == null)) {
            throw new RuntimeException("设置会话属性失败-会话属性键值对有空内容");
        }
        req.getSession().setAttribute(key, value);
    }

    public static Object getRequestSessionAttr(HttpServletRequest req, String key, boolean allow_null) {
        if (!allow_null && isNullOrEmpty(key)) {
            throw new RuntimeException("获取会话属性失败-会话属性是键为空");
        }
        return req.getSession().getAttribute(key);
    }

    public static void removeRequestSessionAttr(HttpServletRequest req, String key) {
        if (isNullOrEmpty(key)) {
            throw new RuntimeException("移除会话属性失败-会话属性是键为空");
        }
        req.getSession().removeAttribute(key);
    }

}