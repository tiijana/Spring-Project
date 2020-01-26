<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coolinarika - SEARCH</title>
<style>
#catIngForm {
 	margin-top: 5%; 
	width: 30%;
 	margin-left: 12%; 
 	margin-right: 2%; 
	background: linear-gradient(to top, #ffffcc 0%, #ccffcc 99%);
	float: left;
}
#catIngTable {
	position:absolute;
 	margin-top: 5px; 
	width: 25%;
	margin-left: -150px;
/* 	margin-right: auto; */
	background: linear-gradient(to top, #ffffcc 0%, #ccffcc 99%);
	bottom: 80px;
	right:25%;
    left:50%;
	
}
th {
	background-color: #ccffcc;
}
</style>
</head>
<body id="backgroundImage">
	<form class="border border-light p-5" action="/Kuvar/recipeController/searchByCategory" method="get" id="catIngForm">

	    <p class="h4 mb-4 text-center">Search by category</p>
	
	    <select class="browser-default custom-select mb-4" id="selected" name="selectedCategory">
	        <option value="" disabled selected>Choose category</option>
	        <c:if test="${!empty categories }">
	        
	        	<c:forEach items="${categories }" var="c">
	        		
	        		<option value="${c.idCategory }">${c.name }</option>
	        
	        	</c:forEach>
	        
	        </c:if>
	    </select>
	
	    <button class="btn btn-info btn-block my-4" type="submit" style="width: 130px;">Show</button>
	
  </form>
    
  	<form class="border border-light p-5" action="/Kuvar/recipeController/searchByIngredient" method="get" id="catIngForm">

	    <p class="h4 mb-4 text-center">Search by ingredient</p>
	
	    <select class="browser-default custom-select mb-4" id="selected" name="selectedIngredient">
	        <option value="" disabled selected>Choose ingredient</option>
	        <c:if test="${!empty ingredients }">
	        
	        	<c:forEach items="${ingredients }" var="i">
	        		
	        		<option value="${i.idIngredient }">${i.name }</option>
	        
	        	</c:forEach>
	        
	        </c:if>
	    </select>
	
	    <button class="btn btn-info btn-block my-4" type="submit" style="width: 130px;">Show</button>
	
  </form>
  
  <c:if test="${!empty recipesByCategory}">
  
  	<table class="table table-striped table-responsive-md btn-table" id="catIngTable">

		  <thead>
		    <tr>
		      <th>Recipe name</th>
		      <th>Posted by</th>
		      <th>Date</th>
		      <th>Show</th>
		    </tr>
		  </thead>
		
		  <tbody>
		  	<c:forEach items="${recipesByCategory }" var="r">
		  		<tr>
		      	<td scope="row"><strong>${r.name }</strong></td>
		      	<td>${r.user.name } ${r.user.surname }</td>
		      	<td>${r.date }</td>
		      	<td><a href="/Kuvar/controller/getRecipeContent?idRec=${r.idRecipe }" class="btn btn-teal btn-rounded btn-sm m-0">Show</a></td>
		    </tr>
		  	</c:forEach>
		  </tbody>
		  
	</table>
	<c:remove var="recipesByCategory"/> 
	
  </c:if> 
  
   <c:if test="${!empty recipesByIngredient}">
  
  	<table class="table table-striped table-responsive-md btn-table" id="catIngTable">

		  <thead>
		    <tr>
		      <th>Recipe name</th>
		      <th>Posted by</th>
		      <th>Date</th>
		      <th>Show</th>
		    </tr>
		  </thead>
		
		  <tbody>
		  	<c:forEach items="${recipesByIngredient }" var="r">
		  		<tr>
		      	<td scope="row"><strong>${r.name }</strong></td>
		      	<td>${r.user.name } ${r.user.surname }</td>
		      	<td>${r.date }</td>
		      	<td><a href="/Kuvar/controller/getRecipeContent?idRec=${r.idRecipe }" class="btn btn-teal btn-rounded btn-sm m-0">Show</a></td>
		    </tr>
		  	</c:forEach>
		  </tbody>
	</table>
		  <c:remove var="recipesByIngredient"/> 
	
  </c:if> 

  		
</body>
</html>