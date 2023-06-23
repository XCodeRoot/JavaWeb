<%--
  Created by IntelliJ IDEA.
  User: 强风吹拂
  Date: 2023/5/15
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath=request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/"
            ;

    pageContext.setAttribute("basePath",basePath);
%>
<!--	base标签,用于固定相对路径跳转的结果	-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
