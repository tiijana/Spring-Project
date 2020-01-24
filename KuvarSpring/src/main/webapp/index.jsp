<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coolinarika</title>
</head>
<body id="backgroundImage">

<img alt="" src="/Kuvar/img/logo.gif" style="margin-left: 35%;">

<!-- 	<div class="view" -->
<!-- 		style="background-image: url('/Kuvar/img/cakeBack.jpg'); background-repeat: no-repeat; background-size: cover; background-position: center center"></div> -->
		
	<div class="background">
		<div class="transbox">
			<c:if test="${!empty recepti }">
				<c:forEach items="${recepti }" var="r">
					<div class="box"></div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</body>
</html>