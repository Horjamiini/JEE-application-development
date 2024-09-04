<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<title>Muokkaus</title>
<link rel="stylesheet" href="css/tyyli.css" />
</head>
<%@ include file="valikko.jsp"%>
<body>

<form action="ServletAsiakasTallenna" method="post">
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
</body>
</html>