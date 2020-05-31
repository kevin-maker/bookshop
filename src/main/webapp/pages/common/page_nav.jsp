<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">${requestScope.page.pageNo - 1}</a>
    </c:if>

    【${requestScope.page.pageNo}】
    <c:if test="${requestScope.page.pageNo < requestScope.page.totalPage}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">${requestScope.page.pageNo + 1}</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.totalPage}">末页</a>
    </c:if>
    共${requestScope.page.totalPage}页，${requestScope.page.totalCount}条记录 到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input id="page_btn" type="button" value="确定">
</div>
