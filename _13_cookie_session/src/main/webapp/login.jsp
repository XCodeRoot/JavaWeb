<%--
  Created by IntelliJ IDEA.
  User: 强风吹拂
  Date: 2023/6/21
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <form action="http://localhost:8080/_13_cookie_session/loginServlet" method="get">
            用户名 : <input type="text" name="username" value="${cookie.username.value}"><br>
            密码  : <input type="password" name="password" ><br>
            <input type="submit" value="登录">

        </form>
</body>
</html>
