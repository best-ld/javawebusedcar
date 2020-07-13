<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="mvc.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/Add.css" type="text/css" rel="stylesheet">
<title>出售车辆</title>
</head>
<body background="background.jpg">
	<%
		User user = (User) request.getAttribute("user");
	%>
	<div>
		<form action="addCar.cardo?user_id=<%=user.getUser_id()%>"
			method="post" enctype="multipart/form-data">
			<br>
			<p class="h">编辑车辆信息</p><br>
			<br>
			<p>用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;名：</p>
			<input disabled="disabled" type="text"
				value="${requestScope.user.user_name}"> <br> <br>
			<p>车&nbsp;牌&nbsp;号&nbsp;码：</p>
			<input name="license_plate" type="text"> <br> <br>
			<p>品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：</p>
			<input name="brand" type="text"> <br> <br>
			<p>型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</p>
			<input name="model" type="text"> <br> <br>
			<p>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限：</p>
			<input name="years" type="text"> <br> <br>
			<p>估价:(/万元)</p>
			<input name="evaluation" type="text"><br> <br>
			<p>该车图片(!必须是.jpg格式)</p>
			<br> <span class="s1">
			<span>上传图片</span>
			<input type="file" name="file">
			</span>
			<p style="color: red; font-size: 15px;">
				<b> <%
 	if (null != request.getAttribute("message")) {
 	out.print(request.getAttribute("message"));
 }
 %>
				</b>
			</p>
			<br> <input type="submit" value="添加" class="si"><br>
			<a
				href="${pageContext.request.contextPath }/sellCar.cardo?user_id=<%=user.getUser_id()%>">放弃添加</a>
		</form>
	</div>
</body>
</html>