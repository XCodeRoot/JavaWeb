<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--	静态包含 base标签/css样式/jQuery文件	--%>
	<%@include file="/pages/common/head.jsp"%>

    <script type="text/javascript">
        $(function (){
            //给删除按钮绑定单击事件
            $("a.deleteItem").click(function(){
                return confirm("确认要删除["+$(this).parent().parent().find("td:first").text()+"]吗?")

            });

            //给清空购物车绑定单击事件
            $("#clearCart").click(function(){
                return confirm("确认要清空购物车吗?")

            });

            //给输入框绑定 内容发生改变事件onchange
            $("input.updateCount").change(function (){
                //获取商品名称
                var name=$(this).parent().parent().find("td:first").text();
                //获取商品数量
                //这里的this指代这个input标签,value就是input标签的属性
                var count=this.value;//这里取出的是,用户修改以后的数量
                //获取bookId
                var id=$(this).attr("bookId");
                if (confirm("你确定要将[ "+ name +" ]商品修改数量为:"+ count+"]吗")){
                    //发起请求,让服务器修改
                    location.href="http://localhost:8080/book/cartServlet?action=updateCount&id="+id+"&count="+count;
                }else{
                    this.value=this.defaultValue;//这里取出的是,用户修改之前的数量
                }
            })
        });
    </script>

</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="" >
			<span class="wel_word">购物车</span>

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
				<td>操作</td>
			</tr>		

            <%--  如果购物车为空,就显示 购物车为空 --%>
            <c:if test="${empty sessionScope.cart.items}">

                <tr>
                    <%--    占据5列 ,超链接让顾客跳转回首页    --%>
                    <td colspan="5"> <a href="index.jsp">亲,当前购物车为空! 快跟小伙伴们浏览商品吧!!!</a> </td>
                </tr>

            </c:if>
            <%--  如果购物车不为空   --%>
            <c:if test="${not empty sessionScope.cart.items}">
                <%--  session域里 有 cart购物车信息, 里面有个Map<Integer,CartItem> items  ,用entry作为循环遍历,来遍历这个map  --%>
                <c:forEach items="${sessionScope.cart.items}" var="entry">
                    <tr>
                        <td>${entry.value.name}</td>
                        <td>
                            <input  bookId="${entry.value.id}"
                                    class="updateCount" style="width:80px" type="text" value="${entry.value.count}">
                        </td>
                        <td>${entry.value.price}</td>
                        <td>${entry.value.totalPrice}</td>
                        <td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                    </tr>
                </c:forEach>
            </c:if>

		</table>

        <%--   如果购物车没有商品,则不用显示这东西     --%>
        <c:if test="${not empty sessionScope.cart.items}">
            <div class="cart_info">
                <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
                <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
                <span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
                <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
            </div>
        </c:if>
	</div>

	<%-- 静态包含 页脚内容	--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>