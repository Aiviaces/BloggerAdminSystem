package com.hrbu.blog.blogadminsystem.Util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

    private static SqlSessionFactory sessionFactory = null;
    /*
     * 创建本地线程变量，为每一个线程独立管理一个session对象 每一个线程只有且仅有单独且唯一的一个session对象
     * 加上线程变量对session进行管理，可以保证线程安全，避免多实例同时调用同一个session对象
     * 每一个线程都会new一个线程变量，从而分配到自己的session对象
     */
    private static final ThreadLocal<SqlSession> threadlocal = new ThreadLocal<>();

    // 创建sessionFactory对象，因为整个应用程序只需要一个实例对象，故用静态代码块
    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybits-config.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回sessionFactory对象 工厂对象
     *
     * @return sessionFactory
     */
    public static SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 新建session会话，并把session放在线程变量中
     */
    private static void newSession() {
        // 打开一个session会话
        SqlSession session = sessionFactory.openSession();
        // 将session会话保存在本线程变量中
        threadlocal.set(session);
    }

    /**
     * 返回session对象              !通过该函数获取到会话
     * @return session
     */
    public static SqlSession getSession(){
        //优先从线程变量中取session对象
        SqlSession session = threadlocal.get();
        //如果线程变量中的session为null，
        if(session==null){
            //新建session会话，并把session放在线程变量中
            newSession();
            //再次从线程变量中取session对象
            session = threadlocal.get();
        }
        return session;
    }

    /**
     * 关闭session对象，并从线程变量中删除        !使用完毕注意关闭
     */
    public static void closeSession(){
        //读取出线程变量中session对象
        SqlSession session = threadlocal.get();
        //如果session对象不为空，关闭sessoin对象，并清空线程变量
        if(session!=null){
            session.close();
            threadlocal.set(null);
        }
    }

}