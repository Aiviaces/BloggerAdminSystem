<%--
  Created by IntelliJ IDEA.
  User: Aiviaces
  Date: 2023/6/22
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>head</title>
    <link rel="stylesheet" href="sources/css/head_left.css"/>
    <script src="sources/javascript/lib/jquery-3.5.1/jquery-3.5.1.min.js"></script>
    <script src="sources/javascript/head_menu_Functions.js" defer="defer"></script>
</head>
<body>
<div id="head" class="head">
    <div id="menu" class="menu">
        <ul id="admin_oprate">
            <%--每一个li接一个ul才能收放,否则点了没用--%>
            <li id="index">管理主页</li>
            <li id="post_admin">文章管理</li>
            <ul>
                <li data-op="post_add">
                    文章发布
                </li>
                <li data-op="post_operate">
                    文章操作
                </li>
                <li data-op="post_review">
                    文章审核
                </li>
            </ul>
            <li id="user_admin">用户管理</li>
            <ul>
                <%-- 前端交给组长(我)搞定,这里data-op作用相当于name,标识操作,方便我js绑定事件 --%>
                <%-- 你们可以先把表单写好,看得明白代码也可以在里面补充测试觉得ok后,把代码提交给我,我来整合 --%>
                <li data-op="user_operate">
                    用户操作
                    <%-- 用户的增删改查都可以放一起,分页查询之后我会修改查询的sql,表单只需记得传页数 --%>
                </li>
                <li data-op="permissionGroup_operate">
                    权限组操作
                </li>
            </ul>
        </ul>
    </div>
    <nav id="nav" class="nav">
        <div id="menu_button" class="menu_button">Menu</div>
    </nav>
</div>

</body>
</html>
