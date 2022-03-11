<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>startPage</title>
</head>
<body>
	<form action="create">
		Writer : <input type="text" name="writer" value="${dto.writer }"> <br>
		Content : <input type="text" name="content" value="${dto.content }"> <br>
		<input type="submit" value="send"> <br>
		${message }
	</form>
</body>
</html>