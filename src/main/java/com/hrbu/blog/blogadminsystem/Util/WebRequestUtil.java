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
    public static String getRequestParameterString(HttpServletRequest request, String paramName,boolean allow_null_or_empty) throws WebParamIllegalException {
        String param=request.getParameter(paramName);
        if (!allow_null_or_empty&& isNullOrEmpty(param))
        {
            throw new WebParamIllegalException(paramName);
        }
        return param;
    }

    public static int getRequestParameterInt(HttpServletRequest request, String paramName,boolean allow_null_or_empty) throws WebParamIllegalException {
        if (allow_null_or_empty){
            return 0;
        }
        else{
            return Integer.parseInt(getRequestParameterString(request, paramName, false));
        }
    }

}