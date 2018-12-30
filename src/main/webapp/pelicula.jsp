<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,es.salesianos.model.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index page</title>
</head>
<body>

<%
List<Pelicula> listAllPeliculas = (List<Pelicula>)request.getAttribute("listAllPeliculas");
%>
	<form action="/pelicula" method="post">
		<span>cod:</span> <input type="text" name="cod"> <br />
		<span>title:</span> <input type="text" name="title"> <br />
		<span>codDirector:</span> <input type="text" name="codDirector"> <br />
		<input type="submit">
	</form>
	
	
	
	
<table border="1">
	<thead>
		<tr>
			<td>Cod</td>
			<td>Title</td>
			<td>codDirector</td>
			<td>Eliminar</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="pelicula" items="${listAllPeliculas}">
			<tr>
				<td><c:out value="${pelicula.cod}"/> </td>
				<td><c:out value="${pelicula.title}"/> </td>
				<td><c:out value="${pelicula.codDirector}"/> </td>
				<td><a href="/pelicula?cod=${pelicula.cod}">Eliminar</a> </td>
	    	</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>