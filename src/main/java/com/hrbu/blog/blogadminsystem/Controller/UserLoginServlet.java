package com.hrbu.blog.blogadminsystem.Controller;

import com.hrbu.blog.blogadminsystem.Model.PermissionGroup;
import com.hrbu.blog.blogadminsystem.Model.User;
import com.hrbu.blog.blogadminsystem.Service.PermissionGroupService;
import com.hrbu.blog.blogadminsystem.Service.UserService;
import com.hrbu.blog.blogadminsystem.Util.CommonUtils;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.QueryErrorException;
import com.hrbu.blog.blogadminsystem.Util.WebRequestUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "UserLoginServlet", value = "/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    /**
     * 登录时记录
     * 1. 用户对象
     * 2. 用户对应的权限对象
     * 3. 将权限对象转化为 Map
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = new UserService();
        PermissionGroupService permissionGroupService = new PermissionGroupService();
        try {
            User user = userService.getUser("1");
            PermissionGroup permissionGroup = permissionGroupService.getPermissionGroupByUid(user.getPgroup());
            Map<String, Object> user_pgroup_map = CommonUtils.permissionGroupToMap(permissionGroup);

            WebRequestUtil.setRequestSessionAttr(request, "user", user, true);
            WebRequestUtil.setRequestSessionAttr(request, "user-pgroup-map", user_pgroup_map, true);

        } catch (QueryErrorException e) {
            e.printStackTrace();
        }
    }
}
