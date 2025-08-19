<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookstore Management</title>
<link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/css/style.css">
</head>
<body>
	<%@ include file="index.html" %>
	<div class="center-container">
	<% if(request.getAttribute("book")==null){ %>
		<h1 class="head">Add new book</h1>
		<form action="insert" method="post" class="center-form">
			<label for="title">Title:</label>
			<input type="text" id="title" name="title" placeholder="Enter book title"/>
			<br><br>
			<label for="author">Author:</label>
			<input type="text" id="author" name="author" placeholder="Enter book author"/>
			<br><br>
			<label for="price">Price:</label>
			<input type="number" id="price" name="price" placeholder="Enter book price"/>
			<br><br>
			<button type="submit">Submit</button>
		</form>
	<% } else{ %>
		<h1 class="head">Edit book</h1>
		<form action="update" method="post" class="center-form">
			<input type="hidden" name="id" value="<c:out value='${book.id}'/>">
			<label for="title">Title:</label>
			<input type="text" id="title" name="title" value="<c:out value='${book.title}'/>"/>
			<br>
			<label for="author">Author:</label>
			<input type="text" id="author" name="author" value="<c:out value='${book.author}'/>"/>
			<br>
			<label for="price">Price:</label>
			<input type="number" id="price" name="price" value="<c:out value='${book.price}'/>"/>
			<br>
			<button type="submit">Submit</button>
		</form>
	<% } %>
	</div>
</body>
</html>