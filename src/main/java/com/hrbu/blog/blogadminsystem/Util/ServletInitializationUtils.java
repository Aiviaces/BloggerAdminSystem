package com.hrbu.blog.blogadminsystem.Util;

import com.hrbu.blog.blogadminsystem.Service.PermissionGroupService;
import com.hrbu.blog.blogadminsystem.Service.UserService;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.QueryErrorException;

import javax.servlet.ServletContext;

public class ServletInitializationUtils {

    /**
     * 初始化用户num
     *
     * @param servletContext servlet上下文
     */
    public static void initializeUserNum(ServletContext servletContext) {
        try {
            int userNum = new UserService().getUserNum();
            servletContext.setAttribute("userNum", userNum);

            System.out.println("userNum " + userNum);

        } catch (QueryErrorException e) {
            servletContext.setAttribute("userNum", -1);
            handleQueryError("用户数目(userNum)", e);
        }
    }

    /**
     * 初始化权限组num
     *
     * @param servletContext servlet上下文
     */
    public static void initializePermissionGroupNum(ServletContext servletContext) {
        try {
            int permissionGroupNum = new PermissionGroupService().getPermissionGroupNum();
            servletContext.setAttribute("permissionGroupNum", permissionGroupNum);

            System.out.println("permissionGroupNum " + permissionGroupNum);

        } catch (QueryErrorException e) {
            servletContext.setAttribute("permissionGroupNum", -1);
            handleQueryError("权限组数目(permissionGroupNum)", e);
        }
    }

    /**
     * 处理查询错误
     *
     * @param entity 实体
     * @param e      e
     */
    private static void handleQueryError(String entity, QueryErrorException e) {
        System.out.println("初始查询" + entity + "错误 " + e.getMessage() + "已设置错误代码 -1");
        e.printStackTrace();
    }
}