<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>KayttajaBeanLomake - H01_T03_B</title>
</head>
<body>
	<jsp:useBean id="kayttaja" scope="page" class="Beanit.KayttajaBean_B"/>
	<jsp:setProperty name="kayttaja" property="*"/>
	
	
	<%--Mikäli lomakkeeseen jätetään kenttä tyhjäksi asetetaan olioon
	määritetty oletusarvo --%>
	
	<form action="Kayttajat_B.jsp" method="post">
	Name: <input type="text" placeholder="<jsp:getProperty property="name" name="kayttaja"/>" name="name"><br>
	Address: <input type="text" placeholder="<jsp:getProperty property="address" name="kayttaja"/>" name="address"><br>
	Hometown: <input type="text" placeholder="<jsp:getProperty property="hometown" name="kayttaja"/>" name="hometown"><br>
	Email: <input type="text" placeholder="<jsp:getProperty property="email" name="kayttaja"/>" name="email"><br>
	<input type="submit" value="Send information">
	</form>
	
	Set information:<br>
	<jsp:getProperty property="name" name="kayttaja"/><br>
	<jsp:getProperty property="address" name="kayttaja"/><br>
	<jsp:getProperty property="hometown" name="kayttaja"/><br>
	<jsp:getProperty property="email" name="kayttaja"/><br>
</body>
</html>