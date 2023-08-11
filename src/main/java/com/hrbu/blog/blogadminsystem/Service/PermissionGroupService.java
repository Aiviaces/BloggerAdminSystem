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
    /* TODO 权限操作管理
     *   权限组列表类型为 List<Map<String,Boolean>> permissionGroup;
     *   包含所有(多个)用户组 每个用户组以 uid 为键 一个 Map 为值
     *   每个用户组的 Map中保存 name(组名) index_jsp admin_jsp user_jsp ... 每个页面有一个权限
     *   需要做的事: 先把Dao层中的方法在Service层调用,返回正确的结果
     *   如何使用Dao层,请看注意事项以及参考我的代码,不懂可以查查网络,或者问我
     *   目前先把Dao层里对应的功能使用明白吧
     *   批量操作参数 统一为 List<实体类名> 类型 (封装为列表的操作在servlet做,也就是controller层)
     * */
    public PermissionGroup getPermissionGroupByName(String p_ugroup) throws QueryErrorException {
        SqlSession session = MyBatisUtil.getSession();
        PermissionGroup res;
        try {
            PermissionGroupDao permissionGroupDao = session.getMapper(PermissionGroupDao.class);
            res = permissionGroupDao.getPermissionGroupByName(p_ugroup);
        } catch (Exception e) {
            session.rollback();
            throw new QueryErrorException("业务层-权限-获取权限");
        } finally {
            MyBatisUtil.closeSession();
        }
        return res;
    }

    public List<PermissionGroup> getAllPermissionGroup() throws QueryErrorException {
        SqlSession session = MyBatisUtil.getSession();
        List<PermissionGroup> res;
        try {
            PermissionGroupDao permissionGroupDao = session.getMapper(PermissionGroupDao.class);
            res = permissionGroupDao.getAllPermissionGroup();
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
