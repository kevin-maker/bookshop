<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + request.getContextPath() + "/";
%>
<%--写base标签，永远固定相对路径跳转结果--%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script src="static/js/jquery-3.4.1.js"></script>
