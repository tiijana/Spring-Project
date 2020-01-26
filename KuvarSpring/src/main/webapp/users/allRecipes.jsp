<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
#form{
	margin-top: 10%;
	width: 40%;
 	margin-left: 20px;
 	margin-right: auto; 
 	float: left;
	background: linear-gradient(to top, #ffffcc 0%, #ccffcc 99%);
}

#form2 {
	margin-top: 10%;
	margin-left:auto;
	margin-right: 20px;
	float: right;
	vertical-align: middle;
	background: linear-gradient(to top, #ffffcc 0%, #ccffcc 99%);
	height: 500px;
	width: 40%;
	
}

body {
	background-image: url('/Kuvar/img/flowers.jpg'); 
	background-repeat: repeat-x;
	background-size: contain; 
	background-position: center;
	width: 100%;
}

</style>
<title>Insert title here</title>
</head>
<body>


<div class="border border-light p-5" id="form">
	<c:if test="${!empty allCategories }">
		<c:forEach items="${allCategories }" var="c">
				<div class="list-group">
				  <a href="/Kuvar/recipeController/showRecipesForCategory?categoryId=${c.idCategory }" class="list-group-item list-group-item-action">${c.name }</a>
				</div>
		</c:forEach>
	</c:if>
</div>

<div class="border border-light p-5" id="form2">
	<c:if test="${!empty recipesForCategory }">
		<table class="table table-striped table-responsive-md btn-table">
			<thead>
			  <tr>
			    <th>Name</th>
			    <th>Posted by</th>
			    <th>Show details</th>
			    <th>Add to favourites</th>
			  </tr>
			</thead>
		<c:forEach items="${recipesForCategory }" var="r">
			<tbody>
			  <tr>
			    <th scope="row">
			    	${r.name }
			    </th>
			    <td>
			    	${r.user.name } ${r.user.surname }
			    </td>
			    <td><a class="btn btn-teal btn-rounded btn-sm m-0" href="/Kuvar/controller/getRecipeContent?idRec=${r.idRecipe }" >Show</a></td>
			    <td><a href="/Kuvar/recipeController/showUsersFavouriteCategories?recipeID=${r.idRecipe }"><i class="fas fa-heart"></i></a></td>
			  </tr>
			</tbody>
		</c:forEach>
		<c:remove var="recipesForCategory"/>
	</table>
  </c:if>	
</div>

</body>
</html>