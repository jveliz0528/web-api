<%@page import="ar.com.eduacionit.app.domain.Producto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de producros</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<th>CODIGO</th>
			<th>TITULO</th>
			<th>PRECIO</th>
		</tr>
		<%
			List<Producto> productos = (List<Producto>) session.getAttribute("productos");
		%>
		<%
			for (Producto producto : productos) {
		%>
		<tr>
			<td><%=producto.getId()%></td>
			<td><%=producto.getCodigo()%></td>
			<td><%=producto.getDescripcion()%></td>
			<td><%=producto.getPrecio()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>