package com.hrbu.blog.blogadminsystem.Service;

import com.hrbu.blog.blogadminsystem.Dao.BlogPostDao;
import com.hrbu.blog.blogadminsystem.Model.BlogPost;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.PrimaryKeyDuplicationException;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.QueryErrorException;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.WrongSQLException;
import com.hrbu.blog.blogadminsystem.Util.MyBatisUtil;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class BlogPostService {
    public List<BlogPost> getAllBlogPost() throws QueryErrorException {
        SqlSession session = MyBatisUtil.getSession();
        List<BlogPost> res;
        try {
            BlogPostDao blogPostDao = session.getMapper(BlogPostDao.class);
            res = blogPostDao.getAllBlogPost();
        } catch (Exception e) {
            session.rollback();
            throw new QueryErrorException("业务层-文章-查询所有文章");
        } finally {
            MyBatisUtil.closeSession();
        }
        return res;
    }
    public BlogPost getBologPost(String uid) throws QueryErrorException {
        SqlSession session = MyBatisUtil.getSession();
        BlogPost res;
        try {
            BlogPostDao blogPostDao = session.getMapper(BlogPostDao.class);
            res=blogPostDao.getBlogPostByUid(uid);
        } catch (Exception e) {
            session.rollback();
            throw new QueryErrorException("业务层-文章-查询文章");
        } finally {
            MyBatisUtil.closeSession();
        }
        return res;
    }

    public int addBologPost(List<BlogPost> post) throws PrimaryKeyDuplicationException, WrongSQLException {
        SqlSession session = MyBatisUtil.getSession();
        int cnt = 0;
        try {
            BlogPostDao blogPostDao = session.getMapper(BlogPostDao.class);
            cnt = blogPostDao.addBlogPost(post);
        } catch (PersistenceException e) {
            Throwable cause=e.getCause();
            /*主键重复*/
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                //抛出附带回滚处理的异常以完成回滚
                throw new PrimaryKeyDuplicationException("业务层-文章-添加文章",session);
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
