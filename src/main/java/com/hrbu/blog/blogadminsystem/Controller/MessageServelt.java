package com.hrbu.blog.blogadminsystem.Controller;

import com.hrbu.blog.blogadminsystem.Util.WebRequestUtil;
import com.hrbu.blog.blogadminsystem.Util.WebResponsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MessageServelt", value = "/MessageServelt")
public class MessageServelt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //允许消息为空,但要注意并替换为空串避免报错
        String msg = (String) WebRequestUtil.getRequestSessionAttr(request, "msg", true);
        if (msg == null) msg = "";
        WebResponsUtil.sendTextResponse(response, msg);
        WebRequestUtil.setRequestSessionAttr(request, "msg", "", true);
    }
}
