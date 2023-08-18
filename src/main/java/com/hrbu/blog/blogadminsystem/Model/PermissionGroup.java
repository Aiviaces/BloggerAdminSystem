package com.hrbu.blog.blogadminsystem.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PermissionGroup {

    @JsonProperty("uid")
    private int uid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("p_login")
    private byte p_login;
    @JsonProperty("p_index")
    private byte p_index;
    @JsonProperty("p_admin_pgroup_operate")
    private byte p_admin_pgroup_operate;
    @JsonProperty("p_admin_user_operate")
    private byte p_admin_user_operate;
    @JsonProperty("p_admin_post_operate")
    private byte p_admin_post_operate;
    @JsonProperty("p_admin_post_add")
    private byte p_admin_post_add;
    @JsonProperty("p_admin_post_review")
    private byte p_admin_post_review;

    /**
     * 权限组id 后台代码生成
     */
    private void setUid(byte uid) {
        this.uid = uid;
    }

    /**
     * 权限组名称
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * 登录页权限
     */
    private void setPLogin(byte pLogin) {
        this.p_login = pLogin;
    }

    /**
     * 权限组操作页权限
     */
    private void setPAdminPgroupOperate(byte pAdminPgroupOperate) {
        this.p_admin_pgroup_operate = pAdminPgroupOperate;
    }

    /**
     * 主页权限
     */
    private void setPIndex(byte pIndex) {
        this.p_index = pIndex;
    }

    /**
     * 用户操作页权限
     */
    private void setPAdminUserOperate(byte pAdminUserOperate) {
        this.p_admin_user_operate = pAdminUserOperate;
    }

    /**
     * 文章页操作权限
     */
    private void setPAdminPostOperate(byte pAdminPostOperate) {
        this.p_admin_post_operate = pAdminPostOperate;
    }

    /**
     * 添加文章页权限
     */
    private void setPAdminPostAdd(byte pAdminPostAdd) {
        this.p_admin_post_add = pAdminPostAdd;
    }

    /**
     * 审核文章页权限
     */
    private void setPAdminPostReview(byte pAdminPostReview) {
        this.p_admin_post_review = pAdminPostReview;
    }


    /**
     * 权限组id 后台代码生成
     */
    private int getUid() {
        return this.uid;
    }

    /**
     * 权限组名称
     */
    private String getName() {
        return this.name;
    }

    /**
     * 登录页权限
     */
    private byte getPLogin() {
        return this.p_login;
    }

    /**
     * 权限组操作页权限
     */
    private byte getPAdminPgroupOperate() {
        return this.p_admin_pgroup_operate;
    }

    /**
     * 主页权限
     */
    private byte getPIndex() {
        return this.p_index;
    }

    /**
     * 用户操作页权限
     */
    private byte getPAdminUserOperate() {
        return this.p_admin_user_operate;
    }

    /**
     * 文章页操作权限
     */
    private byte getPAdminPostOperate() {
        return this.p_admin_post_operate;
    }

    /**
     * 添加文章页权限
     */
    private byte getPAdminPostAdd() {
        return this.p_admin_post_add;
    }

    /**
     * 审核文章页权限
     */
    private byte getPAdminPostReview() {
        return this.p_admin_post_review;
    }

    @Override
    public String toString() {
        return "PermissionGroup{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", pLogin=" + p_login +
                ", pIndex=" + p_index +
                ", pAdminPgroupOperate=" + p_admin_pgroup_operate +
                ", pAdminUserOperate=" + p_admin_user_operate +
                ", pAdminPostOperate=" + p_admin_post_operate +
                ", pAdminPostAdd=" + p_admin_post_add +
                ", pAdminPostReview=" + p_admin_post_review +
                '}';
    }
}
