<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test1</title>
</head>
<body>
	<h1><%out.println("#01 Hello World"); %></h1>
	<h1>id = ${id }</h1>
	<h1>name = ${name}</h1>
	<div>레거시는 filter 를 따로 기입해줬어야했는데</div>
	<div>스프링 부트는 한글 인코딩이 자동으로 된다 </div>
</body>
</html>