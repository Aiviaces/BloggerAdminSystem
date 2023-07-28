package com.hrbu.blog.blogadminsystem.Service;

import com.hrbu.blog.blogadminsystem.Dao.UserDao;
import com.hrbu.blog.blogadminsystem.Model.User;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.PrimaryKeyDuplicationException;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.QueryErrorException;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.WrongSQLException;
import com.hrbu.blog.blogadminsystem.Util.MyBatisUtil;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class UserService {

    public List<User> getAllUser() throws QueryErrorException {
        SqlSession session = MyBatisUtil.getSession();
        List<User> res;
        try {
            UserDao userDao = session.getMapper(UserDao.class);
            res = userDao.getAllUser();
        } catch (Exception e) {
            session.rollback();
            throw new QueryErrorException("业务层-用户-查询所有用户");
        } finally {
            MyBatisUtil.closeSession();
        }
        return res;
    }

    public User getUser(String username) throws QueryErrorException {
        SqlSession session = MyBatisUtil.getSession();
        User res;
        try {
            UserDao userDao = session.getMapper(UserDao.class);
            res = userDao.getUserByUsername(username);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new QueryErrorException("业务层-用户-查询用户");
        } finally {
            MyBatisUtil.closeSession();
        }
        return res;
    }

    public int addUser(List<User> user) throws Exception {
        SqlSession session = MyBatisUtil.getSession();
        int cnt = 0;
        try {
            UserDao userDao = session.getMapper(UserDao.class);
            cnt = userDao.addUser(user);

        } catch (PersistenceException e) {
            Throwable cause=e.getCause();
            /*主键重复*/
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                //抛出附带回滚处理的异常以完成回滚
                throw new PrimaryKeyDuplicationException("业务层-用户-添加用户",session);
            }else if (cause instanceof SQLException){
                //这里不带第二个会话参数,SQL错误,不回滚
                throw new WrongSQLException("业务层-用户-添加用户");
            }//TODO 如果出现别的错误记得补全这部分代码
        }
        finally {
            session.commit();
            MyBatisUtil.closeSession();
        }
        return cnt;
    }
}

