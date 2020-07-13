<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="mvc.bean.Car"%>
<%@ page import="mvc.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/Delete.css" type="text/css" rel="stylesheet">
<title>关闭车辆信息</title>
</head>
<body background="background.jpg">
	<%
		Car car = (Car) request.getAttribute("car");
		User user = (User) request.getAttribute("user");
	%>
	<div><br>
		<p class="h">删除车辆信息</p>
		<br><br>
		<form
			action="closeCar.cardo?user_id=<%=user.getUser_id()%>&car_id=<%=car.getCar_id()%>"
			method="post">
			<p>用&nbsp;户&nbsp;名&nbsp;：&nbsp;</p>
			<input name="username" type="text"> <br><br>
			<p>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;：</p>
			<input name="password" type="password"> <br><br>
			<p>确认密码：</p>
			<input name="again_password" type="password"> <br><br>
			<p style="color: red; font-size: 18px;">
				<b> <%
					if (null != request.getAttribute("message")) {
						out.print(request.getAttribute("message"));
					}
				%>
				</b>
			</p>
			<br> <br><input type="submit" value="立即关闭"
				class="fs"><br> <br>  <a
				href="${pageContext.request.contextPath }/sellCar.cardo?user_id=<%=user.getUser_id()%>">取消关闭</a>
		</form>
	</div>
</body>
</html>