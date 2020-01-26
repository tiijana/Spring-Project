
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body id="backgroundImage">
	<!-- Material form login -->
	<div class="card" id="login">

		<h5 class="card-header info-color white-text text-center py-4">
			<strong>Sign in</strong>
		</h5>

		<!--Card content-->
		<div class="card-body px-lg-5 pt-0">

			<!-- Form -->
			<form class="text-center" style="color: #757575;" action="${pageContext.request.contextPath}/login" method="post" >

				<!-- Email -->
				<div class="md-form">
					<input type="text" name="username" id="materialLoginFormUsername"
						class="form-control"> <label for="materialLoginFormUsername">Username</label>
				</div>

				<!-- Password -->
				<div class="md-form">
					<input type="password" name="password" id="materialLoginFormPassword"
						class="form-control"> <label
						for="materialLoginFormPassword">Password</label>
				</div>

				<div class="d-flex justify-content-around">
					<div>
						<!-- Remember me -->
						<div class="form-check">
							<input type="checkbox" class="form-check-input"
								id="materialLoginFormRemember"> <label
								class="form-check-label" for="materialLoginFormRemember">Remember
								me</label>
						</div>
					</div>
				</div>

				<!-- Sign in button -->
				<button
					class="btn btn-outline-info btn-rounded btn-block my-4 waves-effect z-depth-0"
					type="submit">Sign in</button>

				<!-- Register -->
				<p>
					Not a member? <a href="/Kuvar/signup.jsp">Register</a>
				</p>


			</form>
			<!-- Form -->

		</div>

	</div>
	<!-- Material form login -->
</body>
</html>