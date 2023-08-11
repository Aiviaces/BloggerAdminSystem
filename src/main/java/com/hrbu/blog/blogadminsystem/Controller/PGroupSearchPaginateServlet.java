package com.hrbu.blog.blogadminsystem.Controller;

import com.hrbu.blog.blogadminsystem.Service.PermissionGroupService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PowerServlet", value = "/PowerServlet")
public class PGroupSearchPaginateServlet extends HttpServlet {
    private PermissionGroupService permissionGroupService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        PermissionGroupService permissionGroupService = new PermissionGroupService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
