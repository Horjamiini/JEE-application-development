<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Palkkalomake</title>
</head>
<body>
<% 	Integer tunnit = Integer.parseInt(request.getParameter("tunnit"));
	Integer tuntipalkka = Integer.parseInt(request.getParameter("tuntipalkka"));
	Float veroprosentti = Float.parseFloat(request.getParameter("veroprosentti"));
	Integer bruttopalkka = tunnit * tuntipalkka;
	Float nettopalkka = bruttopalkka * (1-(veroprosentti/100));
%>
<h1>Tiedot</h1>
<%	out.println("Tunnit: " + tunnit); %>
	<br>	
<%  out.println("Tuntipalkka: " + tuntipalkka); %>
	<br>
<%	out.println("Veroprosentti: " + veroprosentti); %>
	<br />
<h1>Palkka</h1>	
<% 	out.println("Bruttopalkka: " + bruttopalkka); %>
	<br />
<%	out.println("Nettopalkka: " + nettopalkka); %>


</body>
</html>