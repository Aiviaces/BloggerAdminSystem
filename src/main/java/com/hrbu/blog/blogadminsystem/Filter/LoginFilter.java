package com.hrbu.blog.blogadminsystem.Filter;

import com.hrbu.blog.blogadminsystem.Model.User;
import com.hrbu.blog.blogadminsystem.Util.WebRequestUtil;
import com.hrbu.blog.blogadminsystem.Util.Wrapper.UrlRewriterRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/view/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = ((HttpServletRequest) request);
        String url = new String(req.getRequestURL());
        if (!url.contains("/login.jsp")) {
            User user = (User) WebRequestUtil.getRequestSessionAttr(req, "user");
            System.out.println(user);
            if (user == null) {
                HttpServletRequestWrapper wrapper = new UrlRewriterRequestWrapper(req, "/view/login.jsp");
                chain.doFilter(wrapper, response);
            } else chain.doFilter(request, response);
        } else chain.doFilter(request, response);
    }
}
