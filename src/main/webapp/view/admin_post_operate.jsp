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
    <link rel="stylesheet" href="sources/css/inner_post.css"/>
    <script src="sources/javascript/lib/jquery-3.5.1/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="inner_box">
    <div id="inner_post_operate">
        <div>
            <table data-table="post_search">
                <tr>
                    <td>
                        <label>
                            <input alt="全选" id="selectall" type="checkbox"/>
                        </label>
                    </td>
                    <td><span>用户名</span></td>
                    <td><span>邮箱</span></td>
                    <td><span>昵称</span></td>
                    <td><span>密码</span></td>
                    <td><span>用户组</span></td>
                    <td><span>操作</span></td>
                </tr>
            </table>
            <div id="post_pagination" class="post_pagination">
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
