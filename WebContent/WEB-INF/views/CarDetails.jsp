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
<link href="css/Car.css" type="text/css" rel="stylesheet">
<title>车辆详情</title>
</head>
<body background="background.jpg">
	<%
		Car car = (Car) request.getAttribute("car");
	User buyer = (User) request.getAttribute("buyer");
	User seller = (User) request.getAttribute("seller");
	%>
	<div class="header">
		<img class="header" src="header.jpg">
	</div>
	<div class="d1">
		<a
			href="${pageContext.request.contextPath }/bcaktoHomepage.usermaindo?user_id=<%=buyer.getUser_id()%>">回到主页</a>
	</div>
	<div class="d2" style="	height: 1200px;">
		<div class="d3">
			<c:if test="${!empty car.photo}">
				<img src="${car.photo}" class="car">
			</c:if>
		</div>
		<div class="d4">
			<p>用&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;名：${seller.user_name}</p>
			<p>车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主：${seller.name}</p>
			<p>车&nbsp;&nbsp;牌&nbsp;&nbsp;号&nbsp;&nbsp;码：${car.license_plate}</p>
			<p>品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：${car.brand }</p>
			<p>型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：${car.model}</p>
			<p>使&nbsp;&nbsp;用&nbsp;&nbsp;年&nbsp;&nbsp;限：${car.years}</p>
			<p>报&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：${car.evaluation}</p>
			<p>所&nbsp;&nbsp;在&nbsp;&nbsp;城&nbsp;&nbsp;市：${seller.city}</p>
			<p>车主电话号码：${seller.phone}</p>
		</div>
		<div class="d5">
			<form
				action="sendmessage.talkdao?get_id=${seller.user_id}&send_id=${buyer.user_id}&car_id=${car.car_id}"
				method="post">
				<p>留言：</p>
				<textarea name="content"></textarea>
				<br> <br> <input type="submit" value="发布"><br>
				<p style="color: red; font-size: 15px;">
					<b> <%
 	if (null != request.getAttribute("waringmessage")) {
 	out.print(request.getAttribute("waringmessage"));
 }
 %>
					</b>
				</p>
			</form>
			<br>
		</div>
	</div>
	<div class="d6">
		<div class="d7">
			<p class="h">留言区
			<p>
			<p style="color: red; font-size: 15px;">
				<b> <%
 	if (null != request.getAttribute("succeffulmessage")) {
 	out.print(request.getAttribute("succeffulmessage"));
 }
 %>
				</b>
			</p>
		</div>
		<c:if test="${!empty requestScope.messages}">
			<c:forEach items="${requestScope.messages}" var="messages">
				<div class="d8">
					<hr>
					<a class="a1"
						href="${pageContext.request.contextPath }/seebackMessage.talkdao?m_id=${messages.m_id }&user_id=${buyer.user_id}&flag=1">${messages.content }......</a>
				</div>
				<br>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>