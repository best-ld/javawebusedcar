<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="mvc.bean.User"%>
<%@ page import="mvc.bean.Car"%>
<%@ page import="java.util.List"%>
<%@ page import="mvc.bean.SPAndUserAndCar"%>
<%@ page import="mvc.bean.SellPremission"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/Sell.css" type="text/css" rel="stylesheet">
<title>卖车</title>
</head>
<body background="background.jpg">
	<%
		User user = (User) request.getAttribute("user");
	%>
	<div class="header">
		<img class="header" src="header.jpg">
	</div>
	<div class="d1">
		<div class="d2">
			<a
				href="${pageContext.request.contextPath }/addCar.usermaindo?user_id=<%=user.getUser_id()%>">添加新车</a>
		</div>
		<div class="d2">
			<a
				href="${pageContext.request.contextPath }/bcaktoHomepage.usermaindo?user_id=<%=user.getUser_id()%>">我要买车or回到主界面</a>
		</div>
		<div class="d3">
			<form action="searchMyCar.cardo">
				<p class="p1">车牌号码：</p>
				<input name="user_id" type="hidden" value=<%=user.getUser_id()%>>
				<input name="license_plate" type="text" class="i1"> <input
					type="submit" value="查询" class="i2"><br> <br>
			</form>
		</div>
	</div>
	<p class="p2">
		<b> <%
 	if (null != request.getAttribute("searchMyCarwarning")) {
 	out.print(request.getAttribute("searchMyCarwarning"));
 }
 if (null != request.getAttribute("message")) {
 	out.print(request.getAttribute("message"));
 }
 if (null != request.getAttribute("successfulmessage")) {
 	out.print(request.getAttribute("successfulmessage"));
 }
 %></b>
	</p>
	<div class="d4">
		<c:if test="${!empty requestScope.spaucs }">
			<c:forEach items="${requestScope.spaucs}" var="spaucs">
				<div class="d5">
					<div class="d6">
						<c:if test="${!empty spaucs.car.photo}">
							<img src="${spaucs.car.photo}" class="car">
						</c:if>
					</div>
					<div class="d7">
						<p class="p2">
							品牌&型号：&nbsp;&nbsp;${spaucs.car.brand}&nbsp;-&nbsp;
							${spaucs.car.model}&nbsp;&nbsp;&nbsp;<br>估价：
							${spaucs.car.evaluation}万元
						</p>
					</div>
					<div class="d8">
						<a class="a2"
							href="${pageContext.request.contextPath }/editCar.cardo?user_id=<%=user.getUser_id()%>&car_id=${spaucs.car.car_id}">修改该车信息</a>
					</div>
					<div class="d8">
						<a class="a2"
							href="${pageContext.request.contextPath }/deleteCar.cardo?user_id=<%=user.getUser_id()%>&car_id=${spaucs.car.car_id}">关闭该车信息</a>
					</div>
					<div class="d8">
						<a class="a2"
							href="${pageContext.request.contextPath }/seeOneMyCar.cardo?user_id=<%=user.getUser_id()%>&car_id=${spaucs.car.car_id}">查看该车具体情况及评论</a>
					</div>
					<div class="d8">
						<c:if
							test="${spaucs.sellPremission.premission==0 && spaucs.sellPremission.releasing==0 }">
				未被授权
			</c:if>
						<c:if
							test="${spaucs.sellPremission.premission==1 && spaucs.sellPremission.releasing==1 }">
				已发布，<a class="a2"
								href="${pageContext.request.contextPath }/release.cardo?user_id=<%=user.getUser_id()%>&car_id=${spaucs.car.car_id}&flag=0">取消发布</a>？
			</c:if>
						<c:if
							test="${spaucs.sellPremission.premission==1 && spaucs.sellPremission.releasing==0 }">
				已授权，<a class="a2"
								href="${pageContext.request.contextPath }/release.cardo?user_id=<%=user.getUser_id()%>&car_id=${spaucs.car.car_id}&flag=1">前往发布</a>？
			</c:if>
						<c:if
							test="${spaucs.sellPremission.premission==0 && spaucs.sellPremission.releasing==1 }">
				！已下架，请修改相关信息
			</c:if>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>



</body>
</html>