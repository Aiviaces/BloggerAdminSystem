package com.hrbu.blog.blogadminsystem.Controller;

import com.hrbu.blog.blogadminsystem.Util.WebResponsUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. 这里只负责返回数量
 * 2. 实际查询在监听器中
 * 3. 变更数据数量时,记得更改 ServletContent 中存的 xxxNum 属性
 */
@WebServlet(name = "UserNumServlet", value = "/UserNumServlet")
public class UserNumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String userNum=getServletContext().getAttribute("userNum").toString();
        WebResponsUtil.sendTextResponse(response,userNum);
    }
}
