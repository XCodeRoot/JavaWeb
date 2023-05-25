<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--	静态包含 base标签/css样式/jQuery文件	--%>
	<%@include file="/pages/common/head.jsp"%></head>
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
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>

		<%--	静态包含 manager管理模块的菜单	--%>
		<%@include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}">删除</a></td>
				</tr>

			</c:forEach>

			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add">添加图书</a></td>
			</tr>	
		</table>

		<div id="page_nav">
			<%--	当前页大于首页 才显示		--%>
			<c:if test="${requestScope.page.pageNo >1}">
				<a href="manager/bookServlet?action=page&pageNo=1">首页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>


				<%--页码输出的开始--%>
				<c:choose>
					<%--情况 1：如果总页码小于等于 5 的情况，页码的范围是：1-总页码--%>
					<c:when test="${ requestScope.page.pageTotal <= 5 }">
						<c:set var="begin" value="1"/>
						<c:set var="end" value="${requestScope.page.pageTotal}"/>
					</c:when>
					<%--情况 2：总页码大于 5 的情况--%>
					<c:when test="${requestScope.page.pageTotal > 5}">
						<c:choose>
							<%--小情况 1：当前页码为前面 3 个：1，2，3 的情况，页码范围是：1-5.--%>
							<c:when test="${requestScope.page.pageNo <= 3}">
								<c:set var="begin" value="1"/>
								<c:set var="end" value="5"/>
							</c:when>
							<%--小情况 2：当前页码为最后 3 个，8，9，10，页码范围是：总页码减 4 - 总页码--%>
							<c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
								<c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
								<c:set var="end" value="${requestScope.page.pageTotal}"/>
							</c:when>
							<%--小情况 3：4，5，6，7，页码范围是：当前页码减 2 - 当前页码加 2--%>
							<c:otherwise>
								<c:set var="begin" value="${requestScope.page.pageNo-2}"/>
								<c:set var="end" value="${requestScope.page.pageNo+2}"/>
							</c:otherwise>
						</c:choose>
					</c:when>
				</c:choose>

			<%--	提取重复代码	--%>
				<c:forEach begin="${begin}" end="${end}" var="i">
					<c:if test="${i == requestScope.page.pageNo}">
						【${i}】
					</c:if>
					<c:if test="${i != requestScope.page.pageNo}">
						<a href="manager/bookServlet?action=page&pageNo=${i}">${i}</a>
					</c:if>
				</c:forEach>
				<%--页码输出的结束--%>



			<%--	到最后一页则不显示 下一页和末页		--%>
			<c:if test="${requestScope.page.pageNo <requestScope.page.pageTotal}">
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>


			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
			到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
			<script type="text/javascript">
				$(function(){
					//跳到指定的页码
					$("#searchPageBtn").click(function (){
						var pageNo= $("#pn_input").val();

						//判断 pageNo>0 , pageNo<pageTotal
						var pageTotal=${requestScope.page.pageTotal};
						//JavaScript语言 提供了一个location地址栏对象
						//它有一个属性 href , 它可以获取浏览器地址栏中的地址
						if (pageNo<1||pageNo>pageTotal){
							alert("输入错误");
						} else{
							location.href="${pageScope.basePath}manager/bookServlet?action=page&pageNo="+pageNo;
						}

					})
				})
			</script>


		</div>

	</div>

	<%-- 静态包含 页脚内容	--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>