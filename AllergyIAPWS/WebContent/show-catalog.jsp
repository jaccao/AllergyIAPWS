<%@page import="java.sql.Statement"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="database.ProductCatalogTable"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Catalog</title>
</head>
<body>
	<h2>Products</h2>
	<h5>
		<a href="ProductCatalog?action=new">New Product</a>
	</h5>
	<table style="width: 100%">
		<tr>
			<td>Id</td>
			<td>Name</td>
			<td>Description</td>
			<td>Allergy</td>
			<td>Customer</td>
			<td></td>
			<td></td>
		</tr>

		<c:forEach items="${products}" var="p">
			<tr>
				<td>${p.id}</td>
				<td>${p.productName}</td>
				<td>${p.productDescription}</td>
				<td>${p.allergyId}</td>
				<td>${p.customerId}</td>
				<td><a href="ProductCatalog?action=edit&id=${p.id}">Edit</a></td>
				<td><a href="ProductCatalog?action=delete&id=${p.id}">Delete</a></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>