package com.hrbu.blog.blogadminsystem.Util;

import com.hrbu.blog.blogadminsystem.Model.PermissionGroup;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
    /**
     * 权限组转 Map (数据表中权限字段1代表有,其他代表没有,默认0)
     * 方法中已转为 boolean
     *
     * @param permissionGroup 权限组对象
     * @return {@link Map}<{@link String}, {@link Object}> 返回map对象
     */
    public static Map<String, Object> permissionGroupToMap(PermissionGroup permissionGroup) {
        if (permissionGroup == null) return null;
        Map<String, Object> map = new HashMap<>();
        map.put("uid", permissionGroup.getUid());
        map.put("name", permissionGroup.getName());
        map.put("login", 1 == permissionGroup.getPLogin());
        map.put("index", 1 == permissionGroup.getPIndex());
        map.put("admin_pgroup_operate", 1 == permissionGroup.getPAdminPgroupOperate());
        map.put("admin_user_operate", 1 == permissionGroup.getPAdminUserOperate());
        map.put("admin_post_operate", 1 == permissionGroup.getPAdminPostOperate());
        map.put("admin_post_add", 1 == permissionGroup.getPAdminPostAdd());
        map.put("admin_post_review", 1 == permissionGroup.getPAdminPostReview());
        return map;
    }
}
