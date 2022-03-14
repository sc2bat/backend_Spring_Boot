<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userlist</title>
</head>
<body>
	<h1><%out.println("JdbcTemplate : UserList"); %></h1>
	<c:forEach items="${users }" var="dto">
		<h1>${dto.id }/${dto.name }</h1>
	</c:forEach>
</body>
</html>