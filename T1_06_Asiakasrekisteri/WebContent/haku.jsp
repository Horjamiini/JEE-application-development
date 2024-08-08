<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Haku</title>
<link rel="stylesheet" href="css/tyyli.css" />
</head>
<%@ include file="valikko.jsp"%>
<body>

<form name="muokkaaAsiakas" method="post" action="ServletAsiakasBean">
<table>
			
			<tr>
				<th>&nbsp;</th>
				<th>id</th>
				<th>Nimi</th>
				<th>Osoite</th>
				<th>Puhelin</th>
				<th>Email</th>
				<th>Salasana</th>
			</tr>
			<c:forEach items="${requestScope.searchList}" var="search">
				<tr>
					<td align="center"><input type="checkbox" name="id"
						value="${item.id}"></td>
					<td><c:out value="${search.id}" /></td>
					<td><c:out value="${search.nimi}" /></td>
					<td><c:out value="${search.osoite}" /></td>
					<td><c:out value="${search.puhelin}" /></td>
					<td><c:out value="${search.email}" /></td>
					<td><c:out value="${search.salasana}" /></td>
				</tr>
			</c:forEach>
						<tr>
				<td><input type="submit" name="submit" value="Muokkaa"></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>

			</tr>

		</table>
	</form>

</body>
</html>