<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/Signup.css" type="text/css" rel="stylesheet">
<title>会员注册</title>
</head>
<body background="background.jpg">
<div>
<br>
<form action="usersignupServlet" method = "post">
<p class="title"><b>会员注册</b></p><br><br>
<p >用&nbsp&nbsp&nbsp户&nbsp&nbsp&nbsp名：</p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input name = "username" type = "text" >
<br><br>
<p >密&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp码：</p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input name = "password" type = "password" >
<br><br>
<p >确&nbsp认&nbsp密&nbsp码：</p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input name = "again_password" type = "password" >
<br><br>
<p >真&nbsp实&nbsp姓&nbsp名：</p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input name = "name" type = "text" >
<br><br>
<p >身份证号码：</p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input name = "idcard" type = "text" >
<br><br>
<p >电&nbsp话&nbsp号&nbsp码：</p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input name = "phone" type = "text" >
<br><br>
<p>所&nbsp在&nbsp城&nbsp市：</p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input name = "city" type = "text" >
<br><br>
<br>
<input type = "submit" value = "立即注册" class="sub" ><br><br>
<p >
<b class="waring"><%
 if (null != request.getAttribute("message")) {
 out.print(request.getAttribute("message"));
 }
 %></b>
 </p>
</form>
<a class="back" href="UserLogin.jsp">返回登录界面</a>
</div>
</body>
</html>