<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="papu" class="Luokat.AsiakasBean"
             scope="session">
</jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<title>muokkaa.jsp</title>
</head>
<body>

	<%
	/**
	Id: <input type="text" name="id" value="${papu.id}"/>
	<br>
	Nimi: <input type="text" name="nimi" value="${papu.nimi}"/>
	<br>
	Osoite: <input type="text" name="osoite" value="${papu.osoite}"/>
	<br>
	Email: <input type="text" name="email" value="${papu.email}"/>
	<br>
	Puhelin: <input type="text" name="puhelin" value="${papu.puhelin}"/>
	<br>
	Salasana: <input type="text" name="salasana" value="${papu.salasana}"/>
	<br>
	**/ %>
	<form action="ControllerServlet" method="post">
	 Id: <input type="text" name="id" readonly value="${papu.id}">
	 <br>
	 Nimi: <input type="text" name="nimi" value="${papu.nimi}"/>
	 <br>
	 Osoite: <input type="text" name="osoite" value="${papu.osoite}"/>
	 <br>
	 Email: <input type="text" name="email" value="${papu.email}"/>
	 <br>
	 Puhelin: <input type="text" name="puhelin" value="${papu.puhelin}"/>
	 <br>
	 Salasana: <input type="text" name="salasana" value="${papu.salasana}"/>
		<input type="submit" name="Tallenna" value="Tallenna">
	</form>
	
	<a href="lomake.html">Palaa hakulomakkeeseen</a>

    
</body>
</html>