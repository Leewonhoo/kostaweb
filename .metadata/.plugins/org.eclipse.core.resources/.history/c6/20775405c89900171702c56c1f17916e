<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP LIFE CYCLE</title>
</head>
<body>
	<h3>jsp life cycle</h3>
	<%!
		public int RequestCount;
		public void jspInit(){
			System.out.println("1. init/ 초기화");
		}
		public void jspDestroy(){
			System.out.println("3. destroy/ 해제");
		}
	%>
	<% out.print("<h3>h3service</h3>"); %>
	<h3>h3service</h3>
	<p>카운트<%= ++RequestCount %>
</body>
</html>