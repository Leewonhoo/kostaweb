<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index.jsp</h1>
	<hr>
	<form method="get" action="http://localhost:8090/kosta/hello">
		<input type="text"><br>
		<input type="submit" value="get">
	</form>
	<hr>
	<form method="post" action="http://localhost:8090/kosta/hello">
		<input type="text"><br>
		<input type="submit" value="post">
	</form>
	<hr>
	<a href="http://localhost:8090/kosta/hello">a태그 서블릿</a><br>
</body>
</html>