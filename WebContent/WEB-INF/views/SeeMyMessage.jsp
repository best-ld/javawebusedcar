<%@page import="mvc.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/MyMessage.css" type="text/css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body background="background.jpg">
	<%
		User user = (User) request.getAttribute("user");
	%>
	<div class="d1">
		<div class="d2">
			<p>我的留言</p>
		</div>
		<div class="d3">
			<a
				href="${pageContext.request.contextPath }/bcaktoHomepage.usermaindo?user_id=<%=user.getUser_id()%>">返回上一页</a>
		</div>
		<c:if test="${!empty requestScope.allmessages }">
			<c:forEach items="${requestScope.allmessages }" var="allmessages">
				<div class="d4">
					<br> ${user.user_name}:(关于<a
						href="${pageContext.request.contextPath }/detailsCar.cardo?user_id=${user.user_id}&car_id=${allmessages.car.car_id}">
						${ allmessages.car.license_plate}</a>的留言)点击继续提问<br>
					<br> ${allmessages.talkMessage.content} <br>
					<a href="${pageContext.request.contextPath }/deleteMessage.talkdao?user_id=${user.user_id}&m_id=${allmessages.talkMessage.m_id}">删除该留言</a>
					<hr>
					回复：<br>
					<div class="d5">
						<c:if test="${!empty allmessages.backMessages }">
							<c:forEach items="${allmessages.backMessages}" var="backmessage">
								<br>
				用户${backmessage.send_name }:
				${backmessage.content}<br>
							</c:forEach>
						</c:if>
					</div>
					<br>
				</div>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>