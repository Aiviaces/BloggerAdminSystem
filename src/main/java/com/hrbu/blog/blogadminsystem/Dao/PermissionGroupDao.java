package com.hrbu.blog.blogadminsystem.Dao;

import com.hrbu.blog.blogadminsystem.Model.PermissionGroup;

import java.util.List;
import java.util.Map;

public interface PermissionGroupDao {
    int getUserNum();

    int addPermissionGroup(List<Map<String, Boolean>> newgroup);

    PermissionGroup getPermissionGroupByName(String name);

    List<PermissionGroup> getAllPermissionGroup();
}
