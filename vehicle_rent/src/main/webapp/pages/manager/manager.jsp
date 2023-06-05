<%--
  Created by IntelliJ IDEA.
  User: 强风吹拂
  Date: 2023/5/31
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理界面</title>
    <base href="http://localhost:8080/vehicle_rent/">
</head>
<style type="text/css">

    ul li {
        list-style: none;
    }

</style>
<body>

    <div style="float: left;">
        <ul>
            <li><a href="manager/function?action=page" target="target">车辆信息管理</a></li>
            <li><a href="manager/hirerServlet?action=page" target="target">客户信息管理</a></li>
            <li><a href="" target="target">维修厂信息管理</a></li>
            <li><a href="" target="target">门店信息管理</a></li>
            <li><a href="" target="target">租赁合同管理</a></li>
        </ul>
    </div>
</body>
</html>
