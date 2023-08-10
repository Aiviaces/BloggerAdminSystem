package com.hrbu.blog.blogadminsystem.Controller;

import com.hrbu.blog.blogadminsystem.Util.WebResponsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserNumServlet", value = "/UserNumServlet")
public class UserNumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        WebResponsUtil.sendTextResponse(response,getServletContext().getAttribute("userNum").toString());
    }
}
