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
    <title>用户操作</title>
    <link rel="stylesheet" href="sources/css/vars.css"/>
    <link rel="stylesheet" href="sources/css/inner.css"/>
    <link rel="stylesheet" href="sources/css/animations.css"/>
    <link rel="stylesheet" href="sources/css/inner_user.css"/>
    <script src="sources/javascript/lib/jquery-3.5.1/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="inner_box">
    <div id="inner_user_operate">
        <div>
            <div>
                <table data-table="user_add">
                    <tr>
                        <td>
                            添加用户
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
            <table data-table="user_search">
                <tr id="user_search_head">
                    <td>
                        <label>
                            <input alt="全选" type="checkbox"/>
                        </label>
                    </td>
                    <td>用户名</td>
                    <td>邮箱</td>
                    <td>昵称</td>
                    <td>密码</td>
                    <td>用户组</td>
                    <td>操作</td>
                </tr>
            </table>
            <div class="user_pagination">
                <a>首页</a>
                <a>上一页</a>
                <a>下一页</a>
                <a>末页</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
