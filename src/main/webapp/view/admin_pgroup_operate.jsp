<%--
  Created by IntelliJ IDEA.
  User: Aiviaces
  Date: 2023/6/22
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>权限组操作</title>
    <link rel="stylesheet" href="sources/css/vars.css"/>
    <link rel="stylesheet" href="sources/css/inner.css"/>
    <link rel="stylesheet" href="sources/css/animations.css"/>
    <link rel="stylesheet" href="sources/css/inner_pgroup.css"/>
    <script src="sources/javascript/lib/jquery-3.5.1/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="inner_box">
    <div id="inner_pgroup_operate">
        <div>
            <div>
                <table data-table="pgroup_add">
                    <tr>
                        <td>
                            添加用户组
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>
                                <input type="text" data-input="username" placeholder="用户名"/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>
                                <input type="email" data-input="email" placeholder="邮箱"/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>
                                <input type="text" data-input="username" placeholder="昵称"/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>
                                <input type="text" data-input="password" placeholder="密码"/>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a>添加</a>
                            <label>
                                <select data-input="username" style="position: relative;bottom: 2px">
                                    <option selected="selected">用户</option>
                                    <option>管理员</option>
                                </select>
                            </label>
                            <a>重置</a>
                        </td>
                    </tr>
                </table>
                <span onclick="loadUserSearchTable()">查询</span>
            </div>
            <table data-table="pgroup_search">
                <tr>
                    <td>
                        <label>
                            <input alt="全选" id="selectall" type="checkbox"/>
                        </label>
                    </td>
                    <td><span title="UID">UID</span></td>
                    <td><span title="权限组名">权限组名</span></td>
                    <td><span title="登录页">登录页</span></td>
                    <td><span title="权限组页">权限组页</span></td>
                    <td><span title="主页">主页</span></td>
                    <td><span title="用户操作页">用户操作页</span></td>
                    <td><span title="文章操作页">文章操作页</span></td>
                    <td><span title="文章添加页">文章添加页</span></td>
                    <td><span title="文章审核页">文章审核页</span></td>
                    <td><span title="操作">操作</span></td>
                </tr>
            </table>
            <div id="pgroup_pagination" class="pgroup_pagination">
                <a data-paginate="begin">首页</a>
                <a data-paginate="last">上一页</a>
                <a data-paginate="next">下一页</a>
                <a data-paginate="end">末页</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
