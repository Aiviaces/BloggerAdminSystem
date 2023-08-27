package com.hrbu.blog.blogadminsystem.Filter;

import com.hrbu.blog.blogadminsystem.Model.PermissionGroup;
import com.hrbu.blog.blogadminsystem.Model.User;
import com.hrbu.blog.blogadminsystem.Service.PermissionGroupService;
import com.hrbu.blog.blogadminsystem.Service.UserService;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.QueryErrorException;
import com.hrbu.blog.blogadminsystem.Util.UrlUtil;
import com.hrbu.blog.blogadminsystem.Util.WebRequestUtil;
import com.hrbu.blog.blogadminsystem.Util.Wrapper.UrlRewriterRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

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
        HttpServletRequestWrapper wrapper;
        boolean islogin;
        if (!url.contains("/login.jsp")) {
            User user = (User) WebRequestUtil.getRequestSessionAttr(req, "user", true);
            islogin = !(user == null); //是否已经登录
            System.out.println("user " + user);
            if (!islogin) {
                //未登录跳转到登录
                wrapper = new UrlRewriterRequestWrapper(req, "/view/login.jsp");
                WebRequestUtil.setRequestSessionAttr(req, "msg", "抱歉,您尚未登录", false);
                chain.doFilter(wrapper, response);
            } else {
                //登录则判断是否有权限
                Map<?, ?> user_pgroup_map = (Map<?, ?>) WebRequestUtil.getRequestSessionAttr(req, "user-pgroup-map", true);
                System.out.println(user_pgroup_map);
                //获取当前访问页面的无后缀的文件名
                String pagename = UrlUtil.getPageName(url);
                //这里不能是uid,或者name
                System.out.println("pagename: " + pagename);

                //这是后台消息字符串
                String msg;
                //少个无关紧要的字段,临时修补这里
                if ("welcome_admin".equals(pagename)) {
                    pagename = "index";
                    msg = "登录成功";
                } else {
                    msg = "权限验证通过";
                }
                Object allow = user_pgroup_map.get(pagename); //是否有权限
                if (allow == null) {
                    allow = false;
                }
                //屎山代码是如何对堆砌的: 忘了自己写了些啥

                if ((boolean) allow) {
                    chain.doFilter(request, response);
                    WebRequestUtil.setRequestSessionAttr(req, "msg", msg, false);
                } else {
                    msg = "抱歉,您的权限不足,已跳转至主页";
                    wrapper = new UrlRewriterRequestWrapper(req, "/view/welcome_admin.jsp");
                    WebRequestUtil.setRequestSessionAttr(req, "msg", msg, false);
                    chain.doFilter(wrapper, response);
                }
            }
        } else {
            //直接跳转登录页就是这个
            WebRequestUtil.setRequestSessionAttr(req, "msg", "请登录您的账号", false);
            chain.doFilter(request, response);
        }
    }
}
