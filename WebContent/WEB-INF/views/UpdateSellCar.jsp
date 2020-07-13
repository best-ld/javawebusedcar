<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="mvc.bean.Car"%>
<%@ page import="mvc.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/UpdateCar.css" type="text/css" rel="stylesheet">
<title>修改车辆信息</title>
</head>
<body  background="background.jpg">
	<%
		Car car = (Car) request.getAttribute("car");
	User user = (User) request.getAttribute("user");
	%>
	<div>
		<form
			action="updateCar.cardo?user_id=<%=user.getUser_id()%>&car_id=<%=car.getCar_id()%>"
			method="post" enctype="multipart/form-data">
			<br>
			<p class="h">修改车辆信息</p><br><br>
			<p>车&nbsp;牌&nbsp;号&nbsp;码：</p>
			<input name="license_plate" type="text"
				 value="${car.license_plate}">
			<br> <br>
			<p>品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：</p>
			<input name="brand" type="text" 
				value="${car.brand}"> <br> <br>
			<p>型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</p>
			<input name="model" type="text" 
				value="${car.model}"> <br> <br>
			<p>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限：</p>
			<input name="years" type="text" 
				value="${car.years}"> <br> <br>
			<p>估价:(/万元)</p>
			<input name="evaluation" type="text"
				 value="${car.evaluation}"><br>
			<p>该车图片(!必须是.jpg格式)</p>
			<br> <span class="s1"> <span>上传图片</span> <input
				type="file" name="file">
			</span><br><br>
			<p style="color: red; font-size: 15px;">
				<b> <%
 	if (null != request.getAttribute("warningmessage")) {
 	out.print(request.getAttribute("warningmessage"));
 }
 %></b>
			</p>
			<br> <input type="submit" value="立即修改"
				 class="si"><br> <a
				href="${pageContext.request.contextPath }/sellCar.cardo?user_id=<%=user.getUser_id()%>">取消修改or返回上一页</a>
		</form>
	</div>
</body>
</html>