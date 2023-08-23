package com.hrbu.blog.blogadminsystem.Util;

/* 配置访问拦截时的副产物,然后想起可以改web配置,暂且留着这个工具类 */
public class UrlUtil {

    public static boolean isLocalhost(String ipAddress) {
        return "127.0.0.1".equals(ipAddress) || "::1".equals(ipAddress);
    }

    // 获取当前访问页面url的无后缀的文件名
    public static String getPageName(String uri) {
        // 获取最后一个"/"之后的部分（文件名+参数）
        String filenameWithParams = uri.substring(uri.lastIndexOf("/") + 1);
        // 分割文件名和参数
        String[] parts = filenameWithParams.split("\\?");
        String filename = parts[0];
        // 去掉后缀
        return filename.substring(0, filename.lastIndexOf("."));
    }
}
