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
    <title>欢迎页</title>
    <link rel="stylesheet" href="sources/css/vars.css"/>
    <link rel="stylesheet" href="sources/css/inner_index.css"/>
    <link rel="stylesheet" href="sources/css/animations.css"/>
    <script src="sources/javascript/lib/jquery-3.5.1/jquery-3.5.1.min.js"></script>
</head>
<body>
<div class="inner_index">
    登录
    <div style="background-color: #252525;">
        <div class="clock" data-page="login">
            <p id="clock-number"></p>
        </div>
        <div id="loginbox" style="width: 200px;margin: 0 auto">
            <label>
                <input type="text"/>
            </label>
            <label>
                <input type="text"/>
            </label>
            <label>
                <a onclick="login()" style="color: white">登录</a>
            </label>
        </div>
    </div>
</div>
</body>
</html>
