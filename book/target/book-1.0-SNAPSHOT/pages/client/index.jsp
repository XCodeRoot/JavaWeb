<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%--	静态包含 base标签/css样式/jQuery文件	--%>
	<%@include file="/pages/common/head.jsp"%>
</head>

	<script	type="text/javascript">
		$(function(){
			//给加入购物车绑定单击事件
			$("button.addToCart").click(function() {
				//在事件响应的function函数中,有一个this对象,这个this对象,是当前正在响应事件的dom对象
				//说白了就是,当前发生的是点击事件,那么这个this就是表示要点击的这个按钮标签
				//$(this).attr
				// 将this转成jQuery表示,然后使用attr获取属性的值,这个属性是标签里的自定义属性bookId,用来携带参数用的
				var bookId=$(this).attr("bookId");
				//跳转到这个地址
				//location.href="http://localhost:8080/book/cartServlet?action=addItem&id="+bookId;

				//发送ajax请求 ,调用servlet里的ajaxAddItem方法,添加商品到购物车
				$.getJSON(
						"http://localhost:8080/book/cartServlet",
						"action=ajaxAddItem&id="+bookId,
						function (data){
							console.log(data)
							$("#cartTotalCount").text("您的购物车中有 " + data.totalCount + " 件商品");
							$("#cartLastName").text(data.lastName);
						}

				)
			});
		})
	</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="" >
			<span class="wel_word">网上书城</span>
			<div>
				<%-- 如果没登录,则显示登录注册按钮	--%>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				</c:if>
				<%-- 如果登录了,则不用显示登录注册按钮	--%>
				<c:if test="${not empty sessionScope.user}">
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
					<a href="orderServlet?action=showMyOrders">我的订单</a>
					<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
				</c:if>
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">

				<form action="client/bookServlet" method="get">

                    <input type="hidden" name="action" value="pageByPrice">

					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${empty sessionScope.cart.items}">
					<span id="cartTotalCount"> </span>
					<%--  为空时输出这个	--%>
					<div id="cartLastName">
						<span style="color: red">当前购物车为空</span>
					</div>
				</c:if>
				<c:if test="${not empty sessionScope.cart.items}">
					<%--	非空时	--%>
					<span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
					<div>
						您刚刚将<span id="cartLastName" style="color: red">${sessionScope.lastName}</span>加入到了购物车中
					</div>
				</c:if>

			</div>


			<c:forEach items="${requestScope.page.items}" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath}" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.name}</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author}</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${book.price}</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales}</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock}</span>
					</div>
					<div class="book_add">
						<button bookId="${book.id}" class="addToCart">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>

		<%--  静态包含 分页条	--%>
		<%@include file="/pages/common/page_nav.jsp"%>
	
	</div>

	<%-- 静态包含 页脚内容	--%>
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>