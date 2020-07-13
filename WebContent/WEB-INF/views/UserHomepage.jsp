<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="mvc.bean.User"%>
<%@ page import="mvc.bean.Car"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/Home.css" type="text/css" rel="stylesheet">
<title>会员主页</title>
</head>
<body background="background.jpg">
	<div class="header">
		<img class="header" src="header.jpg">
	</div>
	<div class="d1">
		<%
			User user = (User) request.getAttribute("user");
		%><br>
		<div class="d3">
			<p class="name">
				欢迎用户：<%=user.getUser_name()%></p>
		</div>
		<div class="d2">
			<a
				href="${pageContext.request.contextPath }/changeUser.usermaindo?user_id=<%=user.getUser_id()%>">修改个人信息</a>
		</div>
		<div class="d2">
			<a
				href="${pageContext.request.contextPath }/sellCar.cardo?user_id=<%=user.getUser_id()%>">我要卖车</a>
		</div>
		<div class="d2">
			<a
				href="${pageContext.request.contextPath }/seeMyLeaveMessage.talkdao?user_id=<%=user.getUser_id()%>">查看我的留言</a>
		</div>
		<div class="d2">
			<a href="${pageContext.request.contextPath }/backtoLogin.usermaindo">重新登录</a>
		</div>
	</div>
	<div class="d4">
		<div class="d6">
			<form action="searchCar.usermaindo?user_id=<%=user.getUser_id()%>"
				method="post">
				<p class="form">品牌：</p>
				<input name="brand" type="text" class="form">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<p class="form">型号：</p>
				<input name="model" type="text" class="form">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<p class="form">价格（最低价~最高价）：</p>
				<input name="lowprice" type="text" class="form">~ <input
					name="heightprice" type="text" class="form">
				<p class="form">年限：</p>
				<input name="years" type="text" class="form">&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="查      询" class="fs">
			</form>
		</div>
		<div class="d5">
			<form action="toporderCar.cardo?user_id=<%=user.getUser_id()%>"
				method="post">
				<input type="submit" value="按价格升序↑" class="fs">
			</form>
		</div>
		<div class="d5">
			<form action="droporderCar.cardo?user_id=<%=user.getUser_id()%>"
				method="post">
				<input class="fs" type="submit" value="按价格降序↓">
			</form>
		</div>
	</div>
	<br>
	<div class="d7">
		<a
			href="${pageContext.request.contextPath }/bcaktoHomepage.usermaindo?user_id=<%=user.getUser_id()%>">
			<%
				if (null != request.getAttribute("searchcarsuccessmessage")) {
				out.print(request.getAttribute("searchcarsuccessmessage"));
			}
			%>
		</a>
	</div>

	<br>


	<c:if test="${!empty requestScope.cars }">
		<c:forEach items="${requestScope.cars}" var="car">
			<div class="d8">
				<div class="d9">
					<c:if test="${!empty car.photo}">
						<img src="${car.photo}" class="car">
					</c:if>
				</div>
				<div class="d10" style="width:700px;">
					<p class="p1">品牌&型号：&nbsp;&nbsp;&nbsp;${car.brand}&nbsp;-&nbsp;
						${car.model}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;估价：${car.evaluation}万元</p>
				</div>
				<div class="d11">
					<a
						href="${pageContext.request.contextPath }/detailsCar.cardo?user_id=<%=user.getUser_id()%>&car_id=${car.car_id}">查看详情》</a>
				</div>
			</div>
		</c:forEach>
	</c:if>
</body>
</html>