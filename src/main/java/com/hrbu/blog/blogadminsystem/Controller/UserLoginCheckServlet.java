package com.hrbu.blog.blogadminsystem.Controller;

import com.hrbu.blog.blogadminsystem.Util.WebRequestUtil;
import com.hrbu.blog.blogadminsystem.Util.WebResponsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserLoginCheckServlet", value = "/UserLoginCheckServlet")
public class UserLoginCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        WebResponsUtil.sendJsonResponse(response, WebRequestUtil.getRequestSessionAttr(request, "user"));
    }
}
