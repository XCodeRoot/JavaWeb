<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%--	静态包含 base标签/css样式/jQuery文件	--%>
    <%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function (){


        });
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">订单详情</span>

    <%--静态包含 登录成功的菜单	--%>
    <%@include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
        </tr>



            <%--  session域里 有 cart购物车信息, 里面有个Map<Integer,CartItem> items  ,用entry作为循环遍历,来遍历这个map  --%>
            <c:forEach items="${requestScope.orderItems}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.count}</td>
                    <td>${item.price}</td>
                    <td>${item.totalPrice}</td>
                </tr>
            </c:forEach>


    </table>


</div>

<%-- 静态包含 页脚内容	--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>