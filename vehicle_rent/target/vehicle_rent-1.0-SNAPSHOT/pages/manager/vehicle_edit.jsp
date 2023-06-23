<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑车辆信息</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>

		
		<div id="main">
			<form action="manager/function" method="get">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<input type="hidden" name="action" value="${param.method}">
				<input type="hidden" name="vehicle_id" value="${requestScope.vehicle.vehicle_id}">
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
					<tr>
						<td><input name="vehicle_id" type="text" value="${requestScope.vehicle.vehicle_id}"/></td>
						<td><input name="vehicle_type" type="text" value="${requestScope.vehicle.vehicle_type}"/></td>
						<td><input name="vehicle_model" type="text" value="${requestScope.vehicle.vehicle_model}"/></td>
						<td><input name="vehicle_state" type="text" value="${requestScope.vehicle.vehicle_state}"/></td>
						<td><input name="vehicle_rent" type="text" value="${requestScope.vehicle.vehicle_rent}"/></td>
						<td><input name="vehicle_number" type="text" value="${requestScope.vehicle.vehicle_number}"/></td>
						<td><input name="vehicle_img" type="text" value="${requestScope.vehicle.vehicle_img}"/></td>
						<td><input name="shop_id" type="text" value="${requestScope.vehicle.shop_id}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>


</body>
</html>