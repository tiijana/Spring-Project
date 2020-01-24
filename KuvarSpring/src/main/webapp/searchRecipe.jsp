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
	width: 60%;
	margin-left: auto;
	margin-right: auto;
	background: linear-gradient(to top, #ffffcc 0%, #ccffcc 99%);
}
#catIngTable {
	margin-top: 5px;
	width: 40%;
	margin-left: auto;
	margin-right: auto;
	background: linear-gradient(to top, #ffffcc 0%, #ccffcc 99%);
	
}
th {
	background-color: #ccffcc;
}
</style>
</head>
<body id="backgroundImage">
	<form class="border border-light p-5" action="/Kuvar/recipeController/searchByIngredientAndCategory" method="get" id="catIngForm">

	    <p class="h4 mb-4 text-center">Search by category and ingredient</p>
	
	    <select class="browser-default custom-select mb-4" id="selected" name="selectedCategory">
	        <option value="" disabled selected>Choose category</option>
	        <c:if test="${!empty categories }">
	        
	        	<c:forEach items="${categories }" var="c">
	        		
	        		<option value="${c.idCategory }">${c.name }</option>
	        
	        	</c:forEach>
	        
	        </c:if>
	    </select>
	
	    <label for="textInput">Text input Label</label>
	    <input type="text" id="textInput" class="form-control mb-4" placeholder="Text input" name="name">
	
	    <button class="btn btn-info btn-block my-4" type="submit" style="width: 130px;">Sign in</button>
	
  </form>
  
  <c:if test="${!empty recipesByCatAndIng }">
  
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
		  	<c:forEach items="${recipesByCatAndIng }" var="r">
		  		<tr>
		      	<td scope="row"><strong>${r.name }</strong></td>
		      	<td>${r.user.name } ${r.user.surname }</td>
		      	<td>${r.date }</td>
		      	<td><a href="/Kuvar/controller/getRecipeContent?idRec=${r.idRecipe }" class="btn btn-teal btn-rounded btn-sm m-0">Prikazi</a></td>
		    </tr>
		  	</c:forEach>
		  </tbody>

	</table>
	
  </c:if> 
  		
</body>
</html>