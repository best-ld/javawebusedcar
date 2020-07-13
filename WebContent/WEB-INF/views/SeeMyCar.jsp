<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="mvc.bean.Car"%>
<%@ page import="mvc.bean.User"%>
<%@ page import="mvc.bean.TalkMessage"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
@charset "UTF-8";

img.header {
	height: 130px;
	width: 800px;
}

div.header {
	width: 70%;
	text-align: center;
	vertical-align: middle;
	background-color: #fff;
	margin-left: 15%;
}

body {
	background-repeat: no-repeat;
	background-attachment: fixed;
	margin: 0;
	pading: 0;
}

div.d2 {
	width: 70%;
	height: 500px;
	margin-left: 15%;
	background-color: #fff;
	box-shadow: 15px 15px 8px #111;
	border-radius: 10px 10px 5px 5px;
	margin-top: 50px;
}

div.d3 {
	margin-top: 100px;
	height: 330px;
	width: 500px;
	float: left;
	margin-left: 40px;
}

div.d4 {
	float: left;
	margin-left: 140px;
	width: 640px;
	height: 400px;
	margin-top: 50px;
	line-height: 52px;
}

p {
	font-size: 24px;
	color: #111;
}

div.d6 {
	background-color: #ffe;
	width: 70%;
	margin-left: 15%;
	margin-top: 50px;
	margin-bottom: 50px;
	box-shadow: 15px 15px 8px #111;
	border-radius: 10px 10px 5px 5px;
}

div.d7 {
	background-color: #ffe;
	width: 96%;
	height: 80px;
	margin-left: 2%;
	margin-top: 20px;
	line-height: 45px;
	text-align: center;
}
div.d8{
background-color: #ffe;
	width: 96%;
	height: 45px;
	margin-left: 2%;
	margin-top: 12px;
	line-height: 23px;
	font-size: 20px;
}

a {
	font-size: 20px;
	color: #08f;
	text-decoration: none;
}

a:hover {
	color: orange;
	font-size: 21px;
	text-decoration: none
}

div.d9{
    background-color: #eee;
	width: 20%;
	margin-left: 40%;
	height:60px;
	margin-top: 50px;
	margin-bottom: 50px;
	box-shadow: 15px 15px 8px #111;
	border-radius: 10px 10px 5px 5px;
	line-height: 45px;
	text-align: center;
}
</style>
<title>评论区</title>
</head>
<body background="background.jpg">
	<%
		Car car = (Car) request.getAttribute("car");
	User user = (User) request.getAttribute("user");
	List<TalkMessage> messages = (List<TalkMessage>) request.getAttribute("messages");
	%>
	<div class="header">
		<img class="header" src="header.jpg">
	</div>
	<div class="d2">
		<div class="d3">
			<c:if test="${!empty car.photo}">
				<img src="${car.photo}" class="car">
			</c:if>
		</div>
		<div class="d4">
			<p>车牌号码：${car.license_plate}</p>
			<p>品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：${car.brand}</p>
			<p>型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：${car.model}</p>
			<p>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限：${car.years}年</p>
			<p>您的估价：${car.evaluation}万元</p>
		</div>
	</div>

	<div class="d6">
		<div class="d7">
			<b style="font-size: 24px; color: #000;">留言区</b>
		</div>
		<c:if test="${!empty requestScope.messages}">
		<hr>
			<c:forEach items="${requestScope.messages}" var="messages">
				<div class="d8">
					${messages.content }...... <a
						href="${pageContext.request.contextPath }/seebackMessage.talkdao?m_id=${messages.m_id }&user_id=${user.user_id}&flag=0">查看并回复</a>
				</div>
				<hr>
			</c:forEach>
		</c:if>
		<br><br>
	</div>
	<div class="d9">
	<a
		href="${pageContext.request.contextPath }/sellCar.cardo?user_id=<%=user.getUser_id()%>">返回上一页</a></div>
</body>
</html>