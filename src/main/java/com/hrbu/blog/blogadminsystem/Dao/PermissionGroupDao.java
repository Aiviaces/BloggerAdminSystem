package com.hrbu.blog.blogadminsystem.Dao;

import com.hrbu.blog.blogadminsystem.Model.PermissionGroup;
import com.hrbu.blog.blogadminsystem.Model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PermissionGroupDao {

    int getPermissionNum();

    int addPermissionGroup(List<Map<String, Boolean>> newgroup);

    PermissionGroup getPermissionGroupByUid(@Param("uid") String uid);

    List<PermissionGroup> getPermissionGroups(@Param("offset") int offset, @Param("pagesize") int pagesize);

}
