<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LoopTest</title>
</head>
<body>
	<h2>구구콘 2개~9개</h2>
	<hr>
	<table border="1" align="center">
		<tr>
			<% for (int i=2; i<10; i++) { %>
				<td align="center" width="120px"><%= i %>단</td>
			<% } %>
		</tr>
		<% for (int i=1; i<10; i++) { %>
			<tr>
			<% for (int j=2; j<10; j++) { %>
				<td align="center"><%= j %> X <%= i %> = <%=(i*j)%></td>
			<% } %>
			</tr>
		<% } %>
	</table>
</body>
</html>