<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员登录</title>
<link href="css/UserLogin.css" type="text/css" rel="stylesheet">
</head>
<body background="background.jpg">
	<div class="d1">
		<p class="logo">瓜皮二手车</p>
	</div>
	<div class="d2">
	<br>
		<form action="admirlogin.admirdo" method="post">
			<h2 >
				<a href="UserLogin.jsp">会员登录</a>&nbsp&nbsp&nbsp&nbsp|&nbsp&nbsp&nbsp&nbsp管理员登录
			</h2>
			<p>管理员账号：</p>
			<input name="admirname" type="text"><br> <br>
			<p>密&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp码：</p>
			<input name="password" type="password"><br> <br>
			<input class="submit" type="submit" value="登  录"><br> <br>
			<p class="message">
				<b class="waring"> <%
 	if (null != request.getAttribute("message")) {
 	out.print(request.getAttribute("message"));
 }
 %>
				</b>
			</p>
			<br>
		</form>
	</div>
</body>
</html>