<%--
  Created by IntelliJ IDEA.
  User: 强风吹拂
  Date: 2023/5/31
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>车辆信息管理</title>
    <%@include file="/pages/common/head.jsp"%>
</head>

<script type="text/javascript">
    $(function (){
        //给删除的a标签 绑定单击事件,	用于确定删除
        $("a.deleteClass").click(function (){
            //在事件的function()函数中,有一个this对象,这个this对象,是对当前正在响应事件的dom对象
            /*
                confirm是确认提示框函数
                参数是它的内容
                有两个按钮一个是确认一个是取消
                    返回true表示 点了,确认,返回false表示取消
             */

            return  confirm("你确定要删除["+ $(this).parent().parent().find("td:first").text() +"]?");

            //return false//阻止元素的默认行为==不提交请求



        })
    })

</script>


<body>
    <div id="main">
        <table>
            <tr>
                <td>车辆编号</td>
                <td>车辆类型</td>
                <td>车辆型号</td>
                <td>租赁状态</td>
                <td>月租金</td>
                <td>车牌号</td>
                <td>车辆图片</td>
                <td>所在门店编号</td>
                <td colspan="2">操作</td>
            </tr>
            <c:forEach items="${requestScope.page.items}" var="vehicle">
                <tr>
                    <td>${vehicle.vehicle_id}</td>
                    <td>${vehicle.vehicle_type}</td>
                    <td>${vehicle.vehicle_model}</td>
                    <td>${vehicle.vehicle_state}</td>
                    <td>${vehicle.vehicle_rent}</td>
                    <td>${vehicle.vehicle_number}</td>
                    <td>${vehicle.vehicle_img}</td>
                    <td>${vehicle.shop_id}</td>
                    <td><a href=
                        "manager/function?action=getVehicle&vehicle_id=${vehicle.vehicle_id}&method=update&pageNo=${requestScope.page.pageNo}"
                                                >修改</a></td>
                    <td><a class="deleteClass" href=
                        "manager/function?action=delete&vehicle_id=${vehicle.vehicle_id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
                </tr>

            </c:forEach>

            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td><a href="pages/manager/vehicle_edit.jsp?method=add&pageNo=${requestScope.page.pageTotal}">添加车辆</a></td>
            </tr>

        <%--  静态包含 分页条	--%>
        <%@include file="/pages/common/page_nav.jsp"%>
</body>
</html>
