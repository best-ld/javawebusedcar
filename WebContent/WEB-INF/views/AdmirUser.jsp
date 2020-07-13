<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mvc.bean.User"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/AdmirUser.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>管理会员</title>
</head>
<body background="background.jpg">
	<div class="d1">
		<div class="d3">会员信息管理</div>
		<div class="d3">
			<a href="${pageContext.request.contextPath }/releasemessgae.admirdo">出售信息发布管理</a>
		</div>
		<div class="d3">
			<a href="${pageContext.request.contextPath }/carmessage.admirdo">车辆出售信息发布管理</a>
		</div>
		<div class="d3">
			<a href="${pageContext.request.contextPath }/backlogin.admirdo">返回登录界面</a>
		</div>
	</div>
	<div class="d2">
		<table border="1">
			<tr>
				<th>用户名</th>
				<th>真实姓名</th>
				<th>电话号码</th>
				<th>所在城市</th>
				<th>权限</th>
				<th>管理</th>
			</tr>
			<c:if test="${!empty requestScope.users}">
				<c:forEach items="${requestScope.users}" var="user">
					<tr>
						<th>${user.user_name}</th>
						<th>${user.name}</th>
						<th>${user.phone}</th>
						<th>${user.city}</th>
						<th><c:if test="${user.permission==1}">允许访问</c:if> <c:if
								test="${user.permission==0}">禁止访问</c:if></th>
						<th><c:if test="${user.permission==1}">
								<a
									href="${pageContext.request.contextPath }
								/permitvisit.admirdo?user_id=${user.user_id}&flag=0" class="a1">禁止</a>
							</c:if> <c:if test="${user.permission==0}">
								<a
									href="${pageContext.request.contextPath }
								/permitvisit.admirdo?user_id=${user.user_id}&flag=1" class="a1">允许</a>
							</c:if></th>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>