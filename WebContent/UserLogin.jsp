<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/UserLogin.css" type="text/css" rel="stylesheet">
<title>会员登录</title>
</head>
<body background="background.jpg">
<div class="d1">
<p class="logo">瓜皮二手车</p>
</div>
<div class="d2">
<br>
	<form action="userloginServlet" method="post">
		<h2>
			会员登录&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; <a
				href="AdmirLogin.jsp">管理员登录</a>
		</h2>
		<p>用户名：</p>
		<input name="username" type="text"><br> <br>
		<p>密&nbsp;&nbsp;&nbsp;&nbsp;码：</p>
		<input name="password" type="password"><br> <br> <input
			class="submit" type="submit" value="登  录"><br> <br>
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
	<b>没有账户？您可以点击此处进行<a href="UserSignup.jsp">注册</a></b>
</div>

</body>
</html>