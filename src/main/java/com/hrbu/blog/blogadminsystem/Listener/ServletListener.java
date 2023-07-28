package com.hrbu.blog.blogadminsystem.Listener;

import com.hrbu.blog.blogadminsystem.Util.MyBatisUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        /*TODO 这里是整个服务器上下文关闭时做的事*/
        MyBatisUtil.closeSession();   //servlet销毁时销毁会话
    }
}
