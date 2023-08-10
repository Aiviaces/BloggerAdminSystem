package com.hrbu.blog.blogadminsystem.Dao;

import com.hrbu.blog.blogadminsystem.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Mapper
public interface UserDao {
    List<User> getUsers(@Param("offset") int offset,@Param("pagesize") int pagesize);
    User getUserByUsername(String username);
    int getUserNum();
    int addUser(List<User> user);
}
