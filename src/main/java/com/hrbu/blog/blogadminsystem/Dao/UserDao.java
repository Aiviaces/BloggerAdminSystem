package com.hrbu.blog.blogadminsystem.Dao;

import com.hrbu.blog.blogadminsystem.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Mapper
public interface UserDao {
    List<User> getAllUser();
    User getUserByUsername(String username);
    int addUser(List<User> user);
}
