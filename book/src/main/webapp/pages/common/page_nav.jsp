<%--
  Created by IntelliJ IDEA.
  User: 强风吹拂
  Date: 2023/5/25
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--  分页条的开始	--%>
<div id="page_nav">
    <%--	当前页大于首页 才显示		--%>
    <c:if test="${requestScope.page.pageNo >1}">
        <a href="${requestScope.page.url }&pageNo=1">首页</a>
        <a href="${requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">上一页</a>
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
            <a href="${requestScope.page.url }&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%-----------页码输出的结束--%>



    <%--	到最后一页则不显示 下一页和末页		--%>
    <c:if test="${requestScope.page.pageNo <requestScope.page.pageTotal}">
        <a href="${requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">末页</a>
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
                    location.href="${pageScope.basePath}${requestScope.page.url }&pageNo="+pageNo;
                }

            })
        })
    </script>


</div>
<%--  分页条的结束	--%>