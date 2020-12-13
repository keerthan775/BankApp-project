<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Statement Success</title>
</head>
<body>
<%
session=request.getSession();
out.println(session.getAttribute("al"));
%>
</body>
</html>