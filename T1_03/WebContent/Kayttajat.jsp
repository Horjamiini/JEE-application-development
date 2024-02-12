<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KayttajaBeanLomake - H01_T03</title>
</head>
<body>
	<jsp:useBean id="kayttaja" scope="page" class="Beanit.KayttajaBean"/>
	<jsp:setProperty name="kayttaja" property="*"/>
	
	<form action="Kayttajat.jsp" method="post">
	Name: <input type="text" name="name"><br>
	Address: <input type="text" name="address"><br>
	Hometown: <input type="text" name="hometown"><br>
	Email: <input type="text" name="email"><br>
	<input type="submit" value="Send information">
	</form>
	
	Set information:<br>
	<jsp:getProperty property="name" name="kayttaja"/><br>
	<jsp:getProperty property="address" name="kayttaja"/><br>
	<jsp:getProperty property="hometown" name="kayttaja"/><br>
	<jsp:getProperty property="email" name="kayttaja"/><br>
</body>
</html>