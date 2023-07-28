package com.hrbu.blog.blogadminsystem.Util.CustomExceptions;

import org.apache.ibatis.session.SqlSession;

public class QueryErrorException extends Exception{
    public QueryErrorException(String where, SqlSession rollback_session) {
        super(where+" 查询异常!已回滚.");
        rollback_session.rollback();
    }
    public QueryErrorException(String where) {
        super(where+" 查询异常!无回滚处理.");
    }
}
