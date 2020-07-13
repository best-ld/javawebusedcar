<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="mvc.bean.User"%>
<%@ page import="java.util.List"%>
<%@ page import="mvc.bean.Car"%>
<%@ page import="mvc.bean.SPAndUserAndCar"%>
<%@ page import="mvc.bean.SellPremission"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/AdmirUser.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>管理出售信息</title>
</head>
<body background="background.jpg">
	<div class="d1">
		<div class="d3">
			<a href="${pageContext.request.contextPath }/toadmiruser.admirdo">会员信息管理</a>
		</div>
		<div class="d3">出售信息发布管理</div>
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
				<th>车牌</th>
				<th>品牌</th>
				<th>型号</th>
				<th>年限</th>
				<th>估价</th>
				<th>销售状态</th>
				<th>管理</th>
			</tr>
			<c:if test="${!empty requestScope.spucs }">
				<c:forEach items="${requestScope.spucs}" var="spuc">
					<tr>
						<th>${spuc.user.user_name }</th>
						<th>${spuc.user.name }</th>
						<th>${spuc.car.license_plate}</th>
						<th>${spuc.car.brand}</th>
						<th>${spuc.car.model}</th>
						<th>${spuc.car.years}</th>
						<th>${spuc.car.evaluation}万元</th>
						<th><c:if
								test="${spuc.sellPremission.premission==0 && spuc.sellPremission.releasing==0}">未授权</c:if>
							<c:if
								test="${spuc.sellPremission.premission==1 && spuc.sellPremission.releasing==0}">已授权，未发布</c:if>
							<c:if
								test="${spuc.sellPremission.premission==1 && spuc.sellPremission.releasing==1}">已发布</c:if>
							<c:if
								test="${spuc.sellPremission.premission==0 && spuc.sellPremission.releasing==1}">已被下架</c:if>
						</th>
						<th><c:if test="${spuc.sellPremission.premission==0}">
								<a class="a1"
									href="${pageContext.request.contextPath }/updateSellPre.admirdo?s_id=${spuc.sellPremission.s_id}&flag=1">
									授权</a>
							</c:if> <c:if test="${spuc.sellPremission.premission==1}">
								<a class="a1"
									href="${pageContext.request.contextPath }/updateSellPre.admirdo?s_id=${spuc.sellPremission.s_id}&flag=0">
									取消</a>
							</c:if></th>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>