<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form class="border border-light p-5" id="addRecipe" action="/Kuvar/recipeController/addToFavouriteCategory" method="post">

		<p class="h4 mb-4 text-center">Choose one of your favourite categories</p>


		<c:if test="${!empty favsCategories }">
			<select class="browser-default custom-select mb-4" id="select"
				name="selectedFavCategory">
				<c:forEach items="${favsCategories }" var="c">
					<option value="${c.idFavourite_category }">${c.name }</option>
				</c:forEach>
			</select>
		</c:if>

		<button class="btn btn-info btn-block my-4" type="submit" style="width: 130px;">Save</button>
	</form>
</body>
</html>