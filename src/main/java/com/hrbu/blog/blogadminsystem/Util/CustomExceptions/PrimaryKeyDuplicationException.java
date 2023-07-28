package com.hrbu.blog.blogadminsystem.Util.CustomExceptions;

import org.apache.ibatis.session.SqlSession;

public class PrimaryKeyDuplicationException extends Exception{
    public PrimaryKeyDuplicationException(String where, SqlSession rollback_session) {
        super(where+" 插入重复主键.已回滚.");
        rollback_session.rollback();
    }
    public PrimaryKeyDuplicationException(String where)
    {
        super(where+" 插入重复主键.无回滚处理.");
    }
}
