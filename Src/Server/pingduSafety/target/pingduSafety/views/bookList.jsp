<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>author</th>
			<th>操作</th>
		</tr>
		
		<c:forEach items="${bookList }" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.name }</td>
				<td>${book.author }</td>
				<td><a href="#">xxxx</a></td>
			<tr>
		</c:forEach>
	</table>
</body>
</html>