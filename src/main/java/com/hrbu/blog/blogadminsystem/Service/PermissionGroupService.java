package com.hrbu.blog.blogadminsystem.Service;


import com.hrbu.blog.blogadminsystem.Dao.PermissionGroupDao;
import com.hrbu.blog.blogadminsystem.Model.PermissionGroup;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.PrimaryKeyDuplicationException;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.QueryErrorException;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.WrongSQLException;
import com.hrbu.blog.blogadminsystem.Util.MyBatisUtil;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

public class PermissionGroupService {

    public int getPermissionGroupNum() throws QueryErrorException {
        SqlSession session = MyBatisUtil.getSession();
        int res;
        try {
            PermissionGroupDao permissionGroupDao = session.getMapper(PermissionGroupDao.class);
            res = permissionGroupDao.getPermissionNum();
        } catch (Exception e) {
            e.printStackTrace();
            throw new QueryErrorException("业务层-权限组-查询权限组数量");
        } finally {
            MyBatisUtil.closeSession();
        }
        return res;
    }

    public PermissionGroup getPermissionGroupByUid(String uid) throws QueryErrorException {
        SqlSession session = MyBatisUtil.getSession();
        PermissionGroup res;
        try {
            PermissionGroupDao permissionGroupDao = session.getMapper(PermissionGroupDao.class);
            res = permissionGroupDao.getPermissionGroupByUid(uid);
        } catch (Exception e) {
            session.rollback();
            throw new QueryErrorException("业务层-权限-获取权限");
        } finally {
            MyBatisUtil.closeSession();
        }
        return res;
    }

    public List<PermissionGroup> getPermissionGroups(int offset, int pagesize) throws QueryErrorException {
        SqlSession session = MyBatisUtil.getSession();
        List<PermissionGroup> res;
        try {
            PermissionGroupDao permissionGroupDao = session.getMapper(PermissionGroupDao.class);
            res = permissionGroupDao.getPermissionGroups(offset, pagesize);
        } catch (Exception e) {
            session.rollback();
            throw new QueryErrorException("业务层-权限-获取所有权限");
        } finally {
            MyBatisUtil.closeSession();
        }
        return res;
    }

    public int addPermissionGroup(List<Map<String, Boolean>> newgroup) throws WrongSQLException, PrimaryKeyDuplicationException {
        SqlSession session = MyBatisUtil.getSession();
        int cnt = 0;
        try {
            PermissionGroupDao permissionGroupDao = session.getMapper(PermissionGroupDao.class);
            cnt = permissionGroupDao.addPermissionGroup(newgroup);
        } catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                //抛出附带回滚处理的异常以完成回滚
                throw new PrimaryKeyDuplicationException("业务层-权限-添加权限", session);
            } else if (cause instanceof SQLException) {
                //这里不带第二个会话参数,SQL错误,不回滚
                throw new WrongSQLException("业务层-用户-添加用户权限");
            }
        } finally {
            session.commit();
            MyBatisUtil.closeSession();
        }
        return cnt;
    }
}
