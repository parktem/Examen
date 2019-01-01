<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.io.*,java.util.*,es.salesianos.model.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index page</title>
</head>
<body>

	<%
		List<Actor> listAllActores = (List<Actor>)request.getAttribute("listAllActores");
		 request.getAttribute("codPelicula");
	%>

	<table border="1">
		<thead>
			<tr>
				<td>Cod Actor</td>
				<td>Nombre actor</td>
				<td>año</td>
				<td>Seleccionar</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="actor" items="${listAllActores}">
				<tr>
					<td><c:out value="${actor.cod}" /></td>
					<td><c:out value="${actor.nombre}" /></td>
					<td><c:out value="${actor.year}" /></td>
					<td><a href="/fillPeliculaActor?codActor=${actor.cod}&codPelicula=${codPelicula}">Seleccionar Actor</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>