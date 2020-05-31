<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
<meta charset="UTF-8">
<title>结算页面</title>
<%--静态包含base标签，css样式，jquery--%>
<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">结算</span>
		<%--静态包含登陆成功的菜单栏--%>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	<div id="main">
		<h1>你的订单已结算，订单号为: <span style="color: red;">${sessionScope.order}</span></h1>
	</div>
</body>
</html>