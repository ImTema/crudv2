<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/includes.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/books.css">
<html>
<head>
	<title>Bookshelf</title>
</head>
<body>
<h1>Books Data</h1>
<div class="leftbody">
	<div class="edit_form hover">
		<form:form action="/book.do/${page}" modelAttribute="book" method="POST">
			<table>
				<tr>
					<td>Book ID</td>
					<td>
						<form:input path="id" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td>Author</td>
					<td>
						<form:input path="author" readonly="${not isUpd}"/>
					</td>
				</tr>
				<tr>
					<td>Title</td>
					<td>
						<form:input path="title" />
					</td>
				</tr>
				<tr>
					<td>Description</td>
					<td>
						<form:input path="description" />
					</td>
				</tr>
				<tr>
					<td>Print Year</td>
					<td>
						<form:input path="printYear" />
					</td>
				</tr>
				<tr>
					<td>ISBN</td>
					<td>
						<form:input path="isbn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:if test="${isUpd}">
							<input type="submit" name="action" value="Add"/>
						</c:if>
						<c:if test="${not isUpd}">
							<input type="submit" name="action" value="Save" />
							<input type="submit" name="action" value="Delete" />
						</c:if>
						<input type="submit" name="action" value="Clear" />
						<input type="hidden" name="search_field" value="${searchField}"/>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
<div class="rightbody">
	<div class="search_form hover">
		<form action="/book.do/0" method="POST">
			<input type="submit" name="action" value="Find" />
			<input type="text" name="search_field" value="${searchField}"/>
			<input type="submit" name="action" value="Clear" />
		</form>
	</div>
	<div class="output_form">
		<table class="simple-little-table hover" cellspacing="0">
			<tr>
				<th>isRead</th>
				<th>id</th>
				<th>Author</th>
				<th>Title</th>
				<th>Description</th>
				<th>Print Year</th>
				<th>ISBN</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${bookList}" var="b">
				<tr>
					<form:form action="/book.do/${page}" method="POST" modelAttribute="book">
						<input type="hidden" name="id" value="${b.id}"/>
						<td>
							<c:choose>
								<c:when test="${b.isReadAlready==1}">
									<c:out value="read"/>
								</c:when>
								<c:otherwise>
									<input type="submit" name="action" value="Read" />
								</c:otherwise>
							</c:choose>
						</td>
						<td><c:out value="${b.id}"/> </td>
						<td>${b.author}</td>
						<td><c:out value="${b.title}" /></td>
						<td><c:out value="${b.description}" /></td>
						<td><c:out value="${b.printYear}" /></td>
						<td><c:out value="${b.isbn}" /></td>
						<td><input type="submit" name="action" value="Update" /></td>
						<td><input type="submit" name="action" value="Delete" /></td>
						<input type="hidden" name="search_field" value="${searchField}"/>
					</form:form>
				</tr>
			</c:forEach>
		</table>
	</div>
	<table class="paging hover" align="center" cellspacing="0">
		<tr>
			<c:forEach begin="0" end="${count}" var="i">
				<td>
					<form:form action="/book.do/${i}" method="post">
						<c:if test="${i==page}">
							<input class="selected" type="submit" value="${i}"/>
						</c:if>
						<c:if test="${i!=page}">
							<input type="submit" value="${i}"/>
						</c:if>
						<input type="hidden" name="search_field" value="${searchField}"/>
					</form:form>
				</td>
			</c:forEach>
		</tr>
	</table>
</div>
</body>
</html>