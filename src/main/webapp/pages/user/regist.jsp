<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_cn">
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<%--静态包含base标签，css样式，jquery--%>
<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	.login_form{
		height:450px;
		margin-top: 14px;
	}
	.rtxt {
		border: 0 none;
		float: none;
		font-family: "宋体";
		font-size: 14px;
		height: 18px;
		line-height: 18px;
		overflow: hidden;
		padding: 8px 0 10px 8px;
		width: 220px;
		border: 1px #e3e3e3 solid;
	}
	#sub_btn{
		background-color: #39987c;
		border: none;
		color: #fff;
		width: 360px;
		height: 40px;
		margin: 0;
	}
</style>
</head>
<body>
	<div id="login_header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
	</div>
	<div class="login_banner">
		<div id="l_content">
			<span class="login_word">欢迎注册</span>
		</div>
		<div id="content">
			<div class="login_form">
				<div class="login_box">
					<div class="tit">
						<h1>注册会员</h1>
						<span class="errorMsg">
							${empty requestScope.msg ? "请输入用户名和密码" : requestScope.msg}
						</span>
					</div>
					<br />
					<div class="form">
						<form action="user" method="post">
							<input type="hidden" name="action" value="regist">
							<label>用户名称：</label>
							<input value="${requestScope.username}" class="rtxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
							<br />
							<br />
							<label>用户密码：</label>
							<input class="rtxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
							<br />
							<br />
							<label>确认密码：</label>
							<input class="rtxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
							<br />
							<br />
							<label>电子邮件：</label>
							<input value="<%=request.getAttribute("email") == null ? "" : request.getAttribute("email")%>" class="rtxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
							<br />
							<br />
							<label>验证码：</label>
							<input class="rtxt" type="text" style="width: 150px;" id="code" name="code"/>
							<img id="img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px;width: 90px;height: 30px">
							<br />
							<br />
							<input type="submit" value="注册" id="sub_btn" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
			// 页面加载完成之后
			$(function () {
				$("#username").blur(function(){
					//获取用户名
					let username = this.value;
					$.getJSON("http://localhost:8080/bookshop/user","action=ajaxExistUsername&username="+username,function (data) {
						if (data.existsUsername) {
							$("span.errorMsg").text("用户名已存在");
						} else {
							$("span.errorMsg").text("用户名可用");
						}
					});
				})
				//给验证码绑定单击事件
				$("#img").click(function () {
					this.src = "${basePath}/bookshop/kaptcha.jpg"
				})
				// 给注册绑定单击事件
				$("#sub_btn").click(function () {
					//验证用户名：必须由字母，数字下划线组成，并且长度为6到12位
					//1.获取用户名输入框里的内容
					let usernameText = $("#username").val();
					//2.创建正则表达式对象
					let usernamePatt = /^\w{6,12}$/;
					//3.使用 test 方法验证
					if (!usernamePatt.test(usernameText)) {
						//4.提示用户结果
						$("span.errorMsg").text("用户名必须由6到12位字母，数字下划线组成！");
						return false;
					}
					// 验证密码：必须由字母，数字下划线组成，并且长度为6到12位
					//1.获取用户名输入框里的内容
					let passwordText = $("#password").val();
					//2.创建正则表达式对象
					let passwordPatt = /^\w{6,12}$/;
					//3.使用test方法验证
					if (!passwordPatt.test(passwordText)) {
						//4.提示用户结果
						$("span.errorMsg").text("密码必须由6到12位字母，数字下划线组成！");
						return false;
					}
					//验证确认密码：和密码相同
					//1.获取确认密码内容
					let repwdText = $("#repwd").val();
					//2.和密码相比较
					if (repwdText != passwordText) {
						//3.提示用户
						$("span.errorMsg").text("确认密码和密码不一致！");
						return false;
					}
					//邮箱验证：xxxxx@xxx.com
					//1.获取邮箱里的内容
					let emailText = $("#email").val();
					//2.创建正则表达式对象
					let emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
					//3.使用test方法验证是否合法
					if (!emailPatt.test(emailText)) {
						//4.提示用户
						$("span.errorMsg").text("邮箱格式不合法！");
						return false;
					}
					//验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
					let codeText = $("#code").val();
					//去掉验证码前后空格
					//alert("去空格前：[" + codeText + "]")
					codeText = $.trim(codeText);
					//alert("去空格后：[" + codeText + "]")
					if (codeText == null || codeText == "") {
						//4 提示用户
						$("span.errorMsg").text("验证码不能为空！");
						return false;
					}
					$("span.errorMsg").text("");
				});
			})
	</script>
</body>
</html>