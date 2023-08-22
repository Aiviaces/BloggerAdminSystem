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
    <script src="sources/javascript/UserLoginFunctions.js" defer></script>
</head>
<body>
<div class="inner_index">
    <span>博客管理系统</span>
    <div>
        <div id="loginbox">
            <div class="clock" data-page="login">
                <p id="clock-number"></p>
            </div>
            <div>
                <label>
                    <input data-field="username" type="text" placeholder="用户名"/>
                </label>
                <label>
                    <input data-field="password" type="text" placeholder="密码"/>
                </label>
                <label>
                    <input data-field="captcha" type="password" placeholder="验证码"/>
                </label>
                <div id="captcha">
                    <div>
                        <img src="" alt="">
                    </div>
                </div>
            </div>
            <label id="loginbtn">
                <a onclick="login()" style="color: white">登录</a>
            </label>
        </div>
    </div>
</div>
</body>
</html>
