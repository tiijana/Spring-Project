<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">

.boxNumOfRecipes {
	margin-top: 10%;
	width: 50%;
	margin-left: auto;
	margin-right: auto;
	background: linear-gradient(to top, #ffffcc 0%, #ccffcc 99%);
}

body {
	background-image: url('/Kuvar/img/flowers.jpg'); 
	background-repeat: repeat-x;
	background-size: contain; 
	background-position: center;
	width: 100%;
}

</style>
<title>Coolinarika - STATISTIC</title>
</head>
<body>

	<div class="boxNumOfRecipes">
		<p class="h4 mb-4 text-center">Number of recipes for day</p>
		
		<form class="border border-light p-5" action="/Kuvar/categoryController/generateReportDate" method="get">
			<input class="form-control mr-sm-2" type="date" name="date">
			  <button class="btn blue-gradient btn-rounded btn-sm my-0" type="submit">Get report</button>
		</form>
		<br>		
	</div>
	
	<div class="boxNumOfRecipes">
		<form action="/Kuvar/categoryController/generateReportCategory" method="get">
			<c:if test="${!empty informations }">
				<c:forEach items="${informations }" var="i">
					Number of recipes for category ${i.key } is: ${i.value } <br>
				</c:forEach>
			</c:if>
			<br>
			<button class="btn blue-gradient btn-rounded btn-sm my-0" type="submit">Get report</button>
		</form>		
	</div>
	
	

</body>
</html>