package com.hrbu.blog.blogadminsystem.Listener;

import com.hrbu.blog.blogadminsystem.Service.UserService;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.QueryErrorException;
import com.hrbu.blog.blogadminsystem.Util.MyBatisUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {
    /**
     *  初始化获取 用户数目 并加入上下文
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        try {
            ServletContext servletContext=sce.getServletContext();
            int userNum=new UserService().getUserNum();
            servletContext.setAttribute("userNum",userNum);
            System.out.println(servletContext.getAttribute("userNum"));
        } catch (QueryErrorException e) {
            System.out.println("初始查询用户数目错误 "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *  关闭时在销毁一次会话
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        /*TODO 这里是整个服务器上下文关闭时做的事*/
        MyBatisUtil.closeSession();   //servlet销毁时销毁会话
    }
}
