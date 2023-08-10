package com.hrbu.blog.blogadminsystem.Controller;

import com.hrbu.blog.blogadminsystem.Model.User;
import com.hrbu.blog.blogadminsystem.Service.UserService;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.QueryErrorException;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.WebParamIllegalException;
import com.hrbu.blog.blogadminsystem.Util.WebRequestUtil;
import com.hrbu.blog.blogadminsystem.Util.WebResponsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserSearchPaginateServlet", value = "/UserSearchPaginateServlet")
public class UserSearchPaginateServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService=new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            int offset= WebRequestUtil.getRequestParameterInt(request,"offset",false);
            int pagesize= WebRequestUtil.getRequestParameterInt(request,"pagesize",false);
            List<User> users=userService.getUsers(offset,pagesize);
            WebResponsUtil.sendJsonArrayResponse(response,users);
        } catch (QueryErrorException | WebParamIllegalException e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }
}
