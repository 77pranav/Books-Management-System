<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookstore Management</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
	<%@ include file="index.html" %>
	<% if(request.getAttribute("listBooks")!=null){ %>
	<div class="data">
	<table class="first_table">
	<caption>List Of Books</caption>
		<thead>
		<tr>
			<th>Id</th>
			<th>Book</th>
			<th>Author</th>
			<th>Price</th>
			<th>Functions</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${listBooks}">
				<tr>
					<td><c:out value="${book.id}"/></td>
					<td><c:out value="${book.title}"/></td>
					<td><c:out value="${book.author}"/></td>
					<td><c:out value="${book.price}"/></td>
					<td><a href="edit?id=<c:out value='${book.id}'/>">Edit</a>&nbsp;&nbsp;&nbsp;<a href="delete?id=<c:out value='${book.id}'/>">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<%} %>
</body>
</html>