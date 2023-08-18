package com.hrbu.blog.blogadminsystem.Controller;

import com.hrbu.blog.blogadminsystem.Model.PermissionGroup;
import com.hrbu.blog.blogadminsystem.Model.User;
import com.hrbu.blog.blogadminsystem.Service.PermissionGroupService;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.QueryErrorException;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.WebParamIllegalException;
import com.hrbu.blog.blogadminsystem.Util.WebRequestUtil;
import com.hrbu.blog.blogadminsystem.Util.WebResponsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PGroupSearchPaginateServlet", value = "/PGroupSearchPaginateServlet")
public class PGroupSearchPaginateServlet extends HttpServlet {
    private PermissionGroupService permissionGroupService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        permissionGroupService = new PermissionGroupService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            int offset = WebRequestUtil.getRequestParameterInt(request, "offset", false);
            int pagesize = WebRequestUtil.getRequestParameterInt(request, "pagesize", false);
            List<PermissionGroup> permissionGroups = permissionGroupService.getPermissionGroups(offset, pagesize);
            WebResponsUtil.sendJsonArrayResponse(response, permissionGroups);
        } catch (QueryErrorException | WebParamIllegalException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
