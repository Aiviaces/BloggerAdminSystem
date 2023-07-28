package com.hrbu.blog.blogadminsystem.Util.CustomExceptions;

import org.apache.ibatis.session.SqlSession;

public class WrongSQLException extends Exception{
    public WrongSQLException(String where) {
        super(where+" SQL语句异常!");
    }
}
