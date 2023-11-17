<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get/Set Example</title>
</head>
<body>
	Message from request: <b><%= request.getAttribute("mySecretMessage") %></b>
	<br />
	Message from session: <b><%= session.getAttribute("mySecretMessage") %></b>
	<br />
	Message from context: <b><%= request.getServletContext().getAttribute("mySecretMessage") %></b>
	<br />
	Message from EL: <b>${mySecretMessage}</b>
	<br />
	
	<a href="home.jsp">Go Home!</a>
</body>
</html>