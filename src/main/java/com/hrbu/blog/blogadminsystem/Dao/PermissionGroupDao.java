package com.hrbu.blog.blogadminsystem.Dao;

import com.hrbu.blog.blogadminsystem.Model.PermissionGroup;

import java.util.List;

public interface PermissionGroupDao {
    /* TODO 添加权限组(单个多个都视为多个) */
    int addPermissionGroup(List<PermissionGroup> newgroup);
    /* TODO 获取单个权限组 */
    PermissionGroup getPermissionGroupByName();
    /* TODO 获取所有权限组 */
    List<PermissionGroup> getAllPermissionGroupByName();
    /* TODO 其他功能待补充 */
}
