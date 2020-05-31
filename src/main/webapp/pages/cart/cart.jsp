<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%--静态包含base标签，css样式，jquery--%>
<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">购物车</span>
		<%--静态包含登陆成功的菜单栏--%>
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
			<c:forEach items="${sessionScope.cart.items}" var="items">
				<tr>
					<td>${items.value.name}</td>
					<td>
						<input class="updateCount" style="width: 50px;" type="text" bookId="${items.value.id}" value="${items.value.count}">
					</td>
					<td>${items.value.price}</td>
					<td>${items.value.totalPrice}</td>
					<td><a class="deleteItem" href="cart?action=deleteItem&id=${items.value.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a class="clean" href="cart?action=cleanItem">清空购物车</a></span>
				<span class="cart_span"><a href="order?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>
	<script>
		$(function (){
			$(".deleteItem").click(function () {
				return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗")
			});

			$(".clean").click(function () {
				return confirm("你确定要清空购物车吗")
			});

			$(".updateCount").change(function(){
				//获取商品名称
				let name = $(this).parent().parent().find("td:first").text();
				let id = $(this).attr('bookId');
				//获取商品数量
				let count = this.value;
				if(confirm("你确定要将【" + name + "】修改数量为" + count +"吗")){
					//发起请求，给服务器修改保存
					location.href = "http://localhost:8080/bookshop/cart?action=updateCount&count="+count+"&id="+id;
				}else{
					//defaultValue属性是表单项Dom对象的属性，他表示默认的value值
					this.value = this.defaultValue;
				}
			})
		})
	</script>
</body>
</html>