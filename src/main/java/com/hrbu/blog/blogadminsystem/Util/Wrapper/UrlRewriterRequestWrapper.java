package com.hrbu.blog.blogadminsystem.Util.Wrapper;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UrlRewriterRequestWrapper extends HttpServletRequestWrapper {
    private String targetUrl;

    public UrlRewriterRequestWrapper(HttpServletRequest request, String targetUrl) {
        super(request);
        this.targetUrl = targetUrl;
    }

    @Override
    public String getServletPath() {
        return targetUrl;  // 替换Servlet路径
    }

    @Override
    public String getRequestURI() {
        return getContextPath() + targetUrl;  // 替换请求URI
    }

    // 如果需要替换其他URL相关的方法，也可以进行类似的重写
    // 例如 getRequestURL()，getServletContext().getRequestDispatcher()等
}