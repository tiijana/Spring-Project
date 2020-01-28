<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
	body {
		background: linear-gradient(to top, #ffffcc 0%, #ccffcc 99%);
	}
</style>
<title>Coolinarika - MY PROFILE</title>
</head>
<body>
	<div>
		<img alt="" src="${user.picture }" class="avatar rounded-circle d-flex align-self-center mr-2 z-depth-1" style="width:90px; height: 110px;">
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="fb-profile">
				<div class="fb-profile-text">
					<h1>${user.name } ${user.surname }</h1>

				</div>
			</div>
		</div>
	</div>
	<!-- /container fluid-->
	<div class="containerProfile">
		<div class="container">
			<div class="col-sm-8">

				<div data-spy="scroll" class="tabbable-panel">
					<div class="tabbable-line">
						<ul class="nav nav-tabs ">
							<li class="active"><a href="/Kuvar/controller/getNameOfUser" data-toggle="tab" target="_top"><strong> My recipes </strong> </a></li>
							<li><a href="/Kuvar/controller/getFavouriteCategories" data-toggle="tab" target="_top"> <strong> My favourite categories </strong></a></li>
							<li><a href="/Kuvar/controller/getMyFriends" data-toggle="tab"> <strong> My friends </strong></a></li>
							<li><a href="/Kuvar/controller/myFriendRequest" data-toggle="tab"> <i class="fas fa-user-plus"></i> </a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="tab_default_1">

								<h4>RECIPES</h4>
								<br>
								<c:if test="${!empty usersRecipes }">
									<c:forEach items="${usersRecipes }" var="r">

										<div class="list-group">
											  <a href="/Kuvar/searchController/getRecipeContent?idRec=${r.idRecipe }" class="list-group-item list-group-item-action">${r.name }</a>
									    </div>

									</c:forEach>
								</c:if>

							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-sm-4"> 
				<div class="panel panel-default">
					<div class="menu_title"><p style="font-style: bold;"><strong>Find friends</strong> </p></div>
					<div class="panel-body">
						<div class="row">
							<c:if test="${!empty notFriends }">

								<ul class="list-group list-group-flush">
									<c:forEach items="${notFriends }" var="nf">

										<li class="list-group-item">
											<a href="/Kuvar/controller/addFriend?idUser2=${nf.idUser }"><i class="fas fa-user-plus"></i></a> ${nf.name } ${nf.surname } 
										
										</li>
										
									</c:forEach>

								</ul>

							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>





</body>
</html>