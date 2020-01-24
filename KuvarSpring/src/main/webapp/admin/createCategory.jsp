<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">

#addCatg {
	margin-top: 10%;
	width: 50%;
	margin-left: auto;
	margin-right: auto;
	background: linear-gradient(to top, #ffffcc 0%, #ccffcc 99%);
}

</style>
<title>Coolinarika - CREATE CATEGORY</title>
</head>
<body id="backgroundImage">

<div class="enterCategory">
	<form class="border border-light p-5" action="${pageContext.request.contextPath}/categoryController/addNewCategory" method="post" id="addCatg">
	
		<p class="h4 mb-4 text-center">Create category</p>
		
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text" id="inputGroup-sizing-default">Category
					name</span>
			</div>
			<br> <input type="text" class="form-control"
				aria-label="Sizing example input"
				aria-describedby="inputGroup-sizing-default" name="name">
		</div>
		<button id="saveCategory" class="btn btn-info btn-block my-4" type="submit" style="width: 130px;">Save</button>
	</form>
</div>

</body>
</html>