<%--
  Created by IntelliJ IDEA.
  User: Aiviaces
  Date: 2023/6/22
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>用户管理系统</title>
    <style>
        #outframe{
            width: 100%;
            height: 100%;
            overflow: hidden;
            background-color: #ece5e2;
        }
        body{
            width: 100vw;
            height: 100vh;
            overflow: hidden;
            min-width: 600px;
        }
    </style>
    <script src="sources/javascript/lib/jquery-3.5.1/jquery-3.5.1.min.js"></script>
    <script src="sources/javascript/InnerPageFunctions.js" async></script>
    <script src="sources/javascript/UserOperateFunctions.js" defer></script>
    <link rel="stylesheet" href="sources/css/vars.css"/>
    <link rel="stylesheet" href="sources/css/inner_page.css"/>
    <link rel="stylesheet" href="sources/css/inner_index.css"/>
    <link rel="stylesheet" href="sources/css/animations.css"/>
</head>
<body>
    <div id="outframe">
        <section><jsp:include page="view/common/head_left.jsp" /></section>
        <div id="inner_page" class="inner_page">
<%--            <jsp:include page="/view/welcome_admin.jsp" />--%>
        </div>
    </div>
</body>
</html>
