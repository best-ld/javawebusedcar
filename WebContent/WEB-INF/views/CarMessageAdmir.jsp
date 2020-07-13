<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mvc.bean.User"%>
<%@ page import="java.util.List"%>
<%@ page import="mvc.bean.Car"%>
<%@ page import="mvc.bean.CarAndUser"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/AdmirUser.css" type="text/css" rel="stylesheet">
<title>车辆信息管理</title>
</head>
<body background="background.jpg">
	<div class="d1">
		<div class="d3">
			<a href="${pageContext.request.contextPath }/toadmiruser.admirdo">会员信息管理</a>
		</div>
		<div class="d3">
			<a href="${pageContext.request.contextPath }/releasemessgae.admirdo">出售信息发布管理</a>
		</div>
		<div class="d3">车辆出售信息发布管理</div>
		<div class="d3">
			<a href="${pageContext.request.contextPath }/backlogin.admirdo">返回登录界面</a>
		</div>
	</div>
	<div class="d2">
		<table border="1">
			<tr>
				<th>用户名</th>
				<th>真实姓名</th>
				<th>车牌</th>
				<th>品牌</th>
				<th>型号</th>
				<th>年限</th>
				<th>估价</th>
				<th>操作</th>
			</tr>
			<c:if test="${!empty requestScope.cars }">
				<c:forEach items="${requestScope.cars}" var="caruser">
					<tr>
						<th>${caruser.user.user_name }</th>
						<th>${caruser.user.name }</th>
						<th>${caruser.car.license_plate }</th>
						<th>${caruser.car.brand }</th>
						<th>${caruser.car.model }</th>
						<th>${caruser.car.years }</th>
						<th>${caruser.car.evaluation }万元</th>
						<th><a class="a1"
							href="${pageContext.request.contextPath }/offShelves.admirdo?car_id=${caruser.car.car_id}">下架该车</a></th>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>