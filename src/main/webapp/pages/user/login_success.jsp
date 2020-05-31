<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
<meta charset="UTF-8">
<title>登陆成功页面</title>
<%--静态包含base标签，css样式，jquery--%>
<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<%--静态包含登陆成功的菜单栏--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	<div id="main">
		<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
	</div>
</body>
</html>