<%@page import="mvc.bean.Car"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="mvc.bean.User"%>
<%@ page import="mvc.bean.BackMessage"%>
<%@ page import="mvc.bean.TalkMessage"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/BackMessage.css" type="text/css" rel="stylesheet">
<title>回复</title>
</head>
<body background="background.jpg">
	<%
		User geter = (User) request.getAttribute("geter");//回复的接收者
	User user = (User) request.getAttribute("user");
	TalkMessage talkmessage = (TalkMessage) request.getAttribute("talkmessage");
	Car car = (Car) request.getAttribute("car");
	Integer flag = (Integer) request.getAttribute("flag");
	%>
	<div class="d1">
	<div class="d2">	
	<p>用户名：${geter.user_name}:</p><br>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${talkmessage.content}</p></div>
	<%
		if (geter.getUser_id() == user.getUser_id()) {
	%>
	<div style="height:20px;margin-left: 5%;margin-bottom:20px;">
	<a
		href="${pageContext.request.contextPath }/deleteMymessage.talkdao?send_id=${user.user_id}&m_id=${talkmessage.m_id}&get_id=${geter.user_id}">删除</a></div>
	<%
		}
	%>
	
	<form
		action="addbackMessage.talkdao?send_id=${user.user_id}&m_id=${talkmessage.m_id}&get_id=${geter.user_id}&flag=${flag}"
		method="post">
		<textarea name="content"></textarea><div class="d3">
		<br> <input type="submit" value="回复"></div>
	</form>
	<p style="color: red; font-size: 15px;">
		<b> <%
 	if (null != request.getAttribute("waringmessage")) {
 	out.print(request.getAttribute("waringmessage"));
 }
 %>
		</b>
	</p><div class="d4">
	<%
		if (flag == 1) {
	%>
	<a
		href="${pageContext.request.contextPath }/detailsCar.cardo?user_id=<%=user.getUser_id()%>&car_id=${car.car_id}">我想提问or返回上一页</a>
	<%
		} else {
	%>
	<a
		href="${pageContext.request.contextPath }/seeOneMyCar.cardo?user_id=<%=user.getUser_id()%>&car_id=${car.car_id}">回到上一页</a>
	<%
		}
	%></div>
	<div class="d5">
	<c:if test="${!empty requestScope.backMessages}">
		<c:forEach items="${requestScope.backMessages}" var="backmessage">
			<br>
	${backmessage.send_name}:
	<br>
	${backmessage.content}
	</c:forEach>
	</c:if></div></div>
</body>
</html>