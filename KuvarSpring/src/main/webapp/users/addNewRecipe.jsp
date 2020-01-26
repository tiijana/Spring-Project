<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body {
	background-image: url('/Kuvar/img/flowers.jpg'); 
	background-repeat: repeat-x;
	background-size: contain; 
	background-position: center;
	width: 100%;
}
</style>
<title>Coolinarika - ADD RECIPE</title>
</head>
<body >

	<form class="border border-light p-5" id="addRecipe" action="/Kuvar/recipeController/saveNewRecipe" method="post">

		<p class="h4 mb-4 text-center">Add recipe</p>

		<label for="textInput">Name</label> <input type="text" id="textInput"
			class="form-control mb-4" placeholder="Text input" name="name">

		<label for="textInput">Description</label> <input type="text"
			id="textInput" class="form-control mb-4" placeholder="Text input"
			name="description">

		<c:if test="${!empty allCategories }">
			<select class="browser-default custom-select mb-4" id="select"
				name="selectedCategory">
				<option value="" disabled selected>Choose a category</option>
				<c:forEach items="${allCategories }" var="c">
					<option value="${c.idCategory }">${c.name }</option>
				</c:forEach>
			</select>
		</c:if>

		<button class="btn btn-info btn-block my-4" type="submit" style="width: 130px;">Save</button>
	</form>

	<form class="border border-light p-5" id="addRecipe" action="/Kuvar/recipeController/addIngredients" method="post">
	
		<p class="h4 mb-4 text-center">Add ingredients<p>
			
		<br>
		<label for="textInput">Ingredient</label> <input type="text"
			id="textInput" class="form-control mb-4" placeholder="Text input" name="name">

		<label for="textInput">Amount of ingredient</label> <input type="text"
			id="textInput" class="form-control mb-4" placeholder="Text input" name="amount">

		<button class="btn btn-info btn-block my-4" type="submit" style="width: 130px;">Add</button>
	</form>
	
	<form class="border border-light p-5" id="addRecipe" action="/Kuvar/recipeController/addPictureForRecipe" method="post">
	
			<p class="h4 mb-4 text-center">Add images<p>

	 		<label for="textInput">Image path</label> <input type="text" id="textInput" class="form-control mb-4" placeholder="Insert path" name="path">

		<button class="btn btn-info btn-block my-4" type="submit" style="width: 130px;">Add</button>
	</form>


</body>
</html>