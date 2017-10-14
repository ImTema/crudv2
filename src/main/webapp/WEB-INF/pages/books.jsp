<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/pages/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bookshelf</title>
</head>
<body>
<h1>Books Data</h1>
<div class="edit_form">
<form:form action="book.do" method="POST" commandName="book">
	<table>
		<tr>
			<td>Book ID</td>
			<td><form:input path="id" disabled="${isUpdating}"/> </td>
		</tr>
		<tr>
			<td>Author</td>
			<td><form:input path="author" disabled="${isUpdating}"/></td>
		</tr>
		<tr>
			<td>Title</td>
			<td><form:input path="title" /></td>
		</tr>
		<tr>
			<td>Description</td>
			<td><form:input path="description" /></td>
		</tr>
		<tr>
			<td>Print Year</td>
			<td><form:input path="printYear" /></td>
		</tr>
		<tr>
			<td>ISBN</td>
			<td><form:input path="isbn" /></td>
		</tr>
		<form:hidden path="isReadAlready" value="0"/>
		<tr>
			<td colspan="2">
				<input type="submit" name="action" value="Add" />
				<input type="submit" name="action" value="Save" />
				<input type="submit" name="action" value="Delete" />
			</td>
		</tr>
	</table>
</form:form>
</div>
<br>
<div class="output_form">
<table border="1">
	<tr>
		<th>is</th>
		<th>id</th>
		<th>Author</th>
		<th>Title</th>
		<th>Description</th>
		<th>printYear</th>
		<th>isbn</th>
	</tr>
	<c:forEach items="${bookList}" var="book">
	<tr>
		<form:form action="book.do" method="POST" commandName="selectedBook">
		<td><c:out value="${book.isReadAlready}" /></td>
		<td><c:out value="${book.id}"/> </td>
		<td>${book.author}</td>
		<td><c:out value="${book.title}" /></td>
		<td><c:out value="${book.description}" /></td>
		<td><c:out value="${book.printYear}" /></td>
		<td><c:out value="${book.isbn}" /></td>
		<form:hidden path="id" value="${book.id}"/>
		<td><input type="submit" name="action" value="Update" /></td>
		<td><input type="submit" name="action" value="Delete" /></td>
		</form:form>
	</tr>
	</c:forEach>
</table>
</div>
</body>
</html>