<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
 	div.gallery { 
	  margin: auto;
 	  border: 1px solid #ccc; 
 	  display: block;  
 	  position: relative;
 	} 
	
 	div.gallery:hover { 
 	  border: 1px solid #777; 
 	} 
	
 	div.gallery img { 
 	  height: 40%; 
 	  float: left; 
 	  width: 20%;
 	  padding: 5px; 
 	} 
	
</style>
<title>Insert title here</title>
</head>
<body>
	
	<h3 class="text-center"><strong>${recipeCon.name }</strong></h3>
	<p class="text-center">
		Posted by: ${userInfo.name } ${userInfo.surname }
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
	
	<div class="gallery">
		<c:if test="${!empty recipePictures }">
			<c:forEach items="${recipePictures }" var="p">
				<img src="${p.path }" alt="Cinque Terre" width="600" height="400">
			</c:forEach>	
		</c:if>
	</div>


</body>
</html>