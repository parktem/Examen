<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.io.*,java.util.*,es.salesianos.model.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<%
		PeliculaActor selectPeliculaActor = (PeliculaActor)request.getAttribute("selectPeliculaActor");
	%>
	
	<form action="/searchRole" method="post">
	<span>Buscar por personaje: <input type="text" name="role"></span>
	<br />
	<input type="submit">
	</form>
	<br />
	<br />
	
	<table border="1">
		<thead>
			<tr>
				<td>Pelicula en la que participó</td>
				<td>Nombre de Actor</td>
				<td>Año de nacimiento</td>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td><c:out value="${selectPeliculaActor.film.title}" /></td>
					<td><c:out value="${selectPeliculaActor.actor.name}" /></td>
					<td><c:out value="${selectPeliculaActor.role}" /></td>
				</tr>
		</tbody>
	</table>


</body>
</html>