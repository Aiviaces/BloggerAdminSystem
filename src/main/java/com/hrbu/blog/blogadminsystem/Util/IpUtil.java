package com.hrbu.blog.blogadminsystem.Util;

/* 配置访问拦截时的副产物,然后想起可以改web配置,暂且留着这个工具类 */
public class IpUtil {
    public static boolean isLocalhost(String ipAddress){
        return "127.0.0.1".equals(ipAddress) || "::1".equals(ipAddress);
    }
}
