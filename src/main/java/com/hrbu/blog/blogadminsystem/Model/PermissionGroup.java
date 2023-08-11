package com.hrbu.blog.blogadminsystem.Model;

public class PermissionGroup {


    private int uid;
    private String name;
    private byte fixed;
    private byte pLogin;
    private byte pIndex;
    private byte pAdminPgroupOperate;
    private byte pAdminUserOperate;
    private byte pAdminPostOperate;
    private byte pAdminPostAdd;
    private byte pAdminPostReview;

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
     * 是否为固定权限,固定权限不可修改
     */
    private void setFixed(byte fixed) {
        this.fixed = fixed;
    }

    /**
     * 登录页权限
     */
    private void setPLogin(byte pLogin) {
        this.pLogin = pLogin;
    }

    /**
     * 权限组操作页权限
     */
    private void setPAdminPgroupOperate(byte pAdminPgroupOperate) {
        this.pAdminPgroupOperate = pAdminPgroupOperate;
    }

    /**
     * 主页权限
     */
    private void setPIndex(byte pIndex) {
        this.pIndex = pIndex;
    }

    /**
     * 用户操作页权限
     */
    private void setPAdminUserOperate(byte pAdminUserOperate) {
        this.pAdminUserOperate = pAdminUserOperate;
    }

    /**
     * 文章页操作权限
     */
    private void setPAdminPostOperate(byte pAdminPostOperate) {
        this.pAdminPostOperate = pAdminPostOperate;
    }

    /**
     * 添加文章页权限
     */
    private void setPAdminPostAdd(byte pAdminPostAdd) {
        this.pAdminPostAdd = pAdminPostAdd;
    }

    /**
     * 审核文章页权限
     */
    private void setPAdminPostReview(byte pAdminPostReview) {
        this.pAdminPostReview = pAdminPostReview;
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
     * 是否为固定权限,固定权限不可修改
     */
    private byte getFixed() {
        return this.fixed;
    }

    /**
     * 登录页权限
     */
    private byte getPLogin() {
        return this.pLogin;
    }

    /**
     * 权限组操作页权限
     */
    private byte getPAdminPgroupOperate() {
        return this.pAdminPgroupOperate;
    }

    /**
     * 主页权限
     */
    private byte getPIndex() {
        return this.pIndex;
    }

    /**
     * 用户操作页权限
     */
    private byte getPAdminUserOperate() {
        return this.pAdminUserOperate;
    }

    /**
     * 文章页操作权限
     */
    private byte getPAdminPostOperate() {
        return this.pAdminPostOperate;
    }

    /**
     * 添加文章页权限
     */
    private byte getPAdminPostAdd() {
        return this.pAdminPostAdd;
    }

    /**
     * 审核文章页权限
     */
    private byte getPAdminPostReview() {
        return this.pAdminPostReview;
    }

    @Override
    public String toString() {
        return "PermissionGroup{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", fixed=" + fixed +
                ", pLogin=" + pLogin +
                ", pIndex=" + pIndex +
                ", pAdminPgroupOperate=" + pAdminPgroupOperate +
                ", pAdminUserOperate=" + pAdminUserOperate +
                ", pAdminPostOperate=" + pAdminPostOperate +
                ", pAdminPostAdd=" + pAdminPostAdd +
                ", pAdminPostReview=" + pAdminPostReview +
                '}';
    }
}
