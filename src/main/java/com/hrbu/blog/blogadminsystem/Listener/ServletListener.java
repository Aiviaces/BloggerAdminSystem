package com.hrbu.blog.blogadminsystem.Listener;
import com.hrbu.blog.blogadminsystem.Util.MyBatisUtil;
import com.hrbu.blog.blogadminsystem.Util.ServletInitializationUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 初始化获取
 * 1. 用户数目
 * 2. 用户组数目
 * <p>
 * #. 初始化所用方法集中到 ServletInitializationUtils
 * #. 记得每次更新数量的同时更新上行文中的内容
 * #. 如果要考虑并发问题,那么需要做锁,太麻烦了,暂且先不考虑
 * #. 对于那些放到初始变量
 */
@WebListener
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        ServletContext servletContext = sce.getServletContext();
        ServletInitializationUtils.initializeUserNum(servletContext);
        ServletInitializationUtils.initializePermissionGroup(servletContext);
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
