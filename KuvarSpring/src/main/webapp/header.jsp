<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript" src="/Kuvar/js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="/Kuvar/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="/Kuvar/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="/Kuvarjs/mdb.min.js"></script>
<meta charset="UTF-8">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<!-- Bootstrap core CSS -->
<link href="/Kuvar/css/bootstrap.min.css" rel="stylesheet">
<!-- Material Design Bootstrap -->
<link href="/Kuvar/css/mdb.min.css" rel="stylesheet">
<!-- Your custom styles (optional) -->
<link href="/Kuvar/css/style.css" rel="stylesheet">



<!--Main Navigation-->
<header>

	<nav class="navbar navbar-expand-lg navbar-dark default-color">
		<a class="navbar-brand" href="/Kuvar/index.jsp"><strong>COOLINARIKA</strong></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
			
				<s:authorize access="isAuthenticated()">
					<li class="nav-item"><a class="nav-link" href="/Kuvar/recipeController/getAllRecipesCategories">All recipes</a></li>
					<li class="nav-item"><a class="nav-link" href="/Kuvar/recipeController/getCategoriesAndIngredients">Add new recipe</a></li>

					<s:authorize access="hasRole('ADMIN')">
				    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/createCategory.jsp">Create category</a></li>
				    <li class="nav-item"><a class="nav-link" href="/Kuvar/categoryController/getStatisticForCategory">Statistic</a></li>
				</s:authorize>
				</s:authorize>
				
				    <li class="nav-item active"><a class="nav-link" href="/Kuvar/searchController/allCategoriesAndIngredients"><i class="fas fa-search"></i></a></li>

				
			</ul>
			<ul class="navbar-nav nav-flex-icons">
				<li class="nav-item"><a class="nav-link" href="https://www.facebook.com/sweetbitesandwinesipsbytamara/" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
				<li class="nav-item"><a class="nav-link" href="https://www.instagram.com/sweetbitesandwinesipsbytamara/" target="_blank"><i class="fab fa-instagram"></i></a></li>
						
				<s:authorize access="isAuthenticated()">
					<li class="nav-item"><a class="nav-link" href="/Kuvar/controller/getNameOfUser">PROFILE</a></li>
					<li class="nav-item"><a class="nav-link" href="/Kuvar/controller/getMyChatUsers"><i class="far fa-envelope fa-1x"></i></a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">LOG OUT</a></li>
				</s:authorize>
				
				<s:authorize access="!isAuthenticated()">
					<li class="nav-item"><a class="nav-link"
						href="/Kuvar/signup.jsp">SIGN UP</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/Kuvar/login.jsp">LOGIN</a></li>
				</s:authorize>
				
			</ul>
		</div>
	</nav>

</header>
<!--Main Navigation-->









