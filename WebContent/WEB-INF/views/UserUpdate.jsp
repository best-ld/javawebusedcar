<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="mvc.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改个人信息</title>
<link href="css/UpdateUser.css" type="text/css" rel="stylesheet">
</head>
<body background="background.jpg">
	<div class="d1">
	<p class="h">会员信息修改</p>
	<%
		User user = (User) request.getAttribute("user");
	%>

	<form
		action="${pageContext.request.contextPath }/updateUser.usermaindo?user_id=<%=user.getUser_id()%>"
		method="post">
		<p class="p1">用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;名：</p>
		<input name="username" type="text"
			value="${requestScope.user.user_name}"> <br> <br>
		<p class="p1">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</p>
		<input name="password" type="text" 
			value="${requestScope.user.password}"><br> <br>
		<p class="p1">真&nbsp;实&nbsp;姓&nbsp;名：</p>
		<input disabled="disabled" type="text"
			 value="${requestScope.user.name}"><br>
		<br>
		<p class="p1">身份证号码：</p>
		<input disabled="disabled" type="text"
			
			value="${requestScope.user.idcard}"><br> <br>
		<p class="p1">电&nbsp;话&nbsp;号&nbsp;码：</p>
		<input name="phone" type="text" 
			value="${requestScope.user.phone}"><br> <br>
		<p class="p1">所&nbsp;在&nbsp;城&nbsp;市：</p>
		<input name="city" type="text" 
			value="${requestScope.user.city}"><br> <br>
		<p class="p2">
			<b>
				<%
					if (null != request.getAttribute("message")) {
						out.print(request.getAttribute("message"));
					}
				%>
			</b>
		</p>
		<br> <input type="submit" value="立即修改" class="fs"
			><br> <br>
	</form>
	<a class="a1"href="${pageContext.request.contextPath }/bcaktoHomepage.usermaindo?user_id=<%=user.getUser_id()%>">放弃修改</a>
	</div>
</body>
</html>