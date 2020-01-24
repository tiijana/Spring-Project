<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">

#addFavCategory {
	margin-top: 5%;
	width: 60%;
	margin-left: auto;
	margin-right: auto;
	background: linear-gradient(to top, #ffffcc 0%, #ccffcc 99%);
}

</style>
<title>Insert title here</title>
</head>
<body>

	<form class="border border-light p-5" id="addFavCategory" action="/Kuvar/controller/addFavouriteCategory" method="post">

		<p class="h4 mb-4 text-center">New favourite category</p>

		<label for="textInput">Category name</label> <input type="text" id="textInput" class="form-control mb-4" placeholder="Text input" name="name">

		<button class="btn btn-info btn-block my-4" type="submit" style="width: 130px;">Add</button>
	</form>
</body>
</html>