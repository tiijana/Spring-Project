<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta charset="UTF-8">
<style>
	.mySlides {
		display:none;
	}
	body {
		background: linear-gradient(to bottom left, #66ffcc 0%, #ff99ff 99%);
	}
</style>

<title>Insert title here</title>
</head>
<body>
	
	<h3 class="text-center"><strong>${recipeCon.name }</strong></h3>
	<p class="text-center">
		Posted by: ${userInfo.name } ${userInfo.surname } <br>
		Post date: ${recipeCon.date } <br>
		Belongs to the category: ${recipeCon.category.name } <br>
		Description: ${recipeCon.description } <br>
		Ingredients:
		<c:if test="${!empty recipeIngredients }">
			<c:forEach items="${recipeIngredients }" var="i">
				 ${i.name } <br>
			</c:forEach>
		</c:if>
	</p>
	
	<hr>
	
	<div class="w3-content w3-display-container">
		<c:if test="${!empty recipePictures }">
			<c:forEach items="${recipePictures }" var="p">
	  			<img class="mySlides" src="${p.path }" style="width:100%; height:70%;">
	  		</c:forEach>
	    </c:if>
	 	<button class="w3-button w3-black w3-display-left" onclick="plusDivs(-1)">&#10094;</button>
	  	<button class="w3-button w3-black w3-display-right" onclick="plusDivs(1)">&#10095;</button>
	</div>

<script type="text/javascript">
	var slideIndex = 1;
	showDivs(slideIndex);
	
	function plusDivs(n) {
	  showDivs(slideIndex += n);
	}
	
	function showDivs(n) {
	  var i;
	  var x = document.getElementsByClassName("mySlides");
	  if (n > x.length) {slideIndex = 1}
	  if (n < 1) {slideIndex = x.length}
	  for (i = 0; i < x.length; i++) {
	    x[i].style.display = "none";  
	  }
	  x[slideIndex-1].style.display = "block";  
	}
</script>

</body>
</html>