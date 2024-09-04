<%@ page language="java" contentType="text/html; charsetUTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Palkkalomake</title>
</head>
<body>
	<form action="../ReqDispatchServlet" method="post" >
		Tunnit: <input type=number name="tunnit">
		Tuntipalkka: <input type=number name="tuntipalkka">
		Veroprosentti: <input type=number name="veroprosentti">
		<input type=submit value="Lähetä">
	</form>

	<p>
		<%
			if(request.getAttribute("bruttopalkka") != null && request.getAttribute("nettopalkka") != null){
				out.println("Bruttopalkka: " + request.getAttribute("bruttopalkka"));
				out.println("Nettopalkka: " + request.getAttribute("nettopalkka"));
			}
		%>
	</p>
</body>
</html>