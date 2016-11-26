<%@page import="java.sql.Statement"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="database.CustomerTable"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers List</title>
</head>
<body>
	<h2>Customers</h2>
	<h5>
		<a href="Customers?action=new">New Customer</a>
	</h5>
	<table style="width: 100%">
		<tr>
			<td>Id</td>
			<td>Name</td>
			<td>Password</td>
			<td>Company</td>
		</tr>

		<c:forEach items="${customers}" var="p">
			<tr>
				<td>${p.id}</td>
				<td>${p.userName}</td>
				<td>${p.userPassword}</td>
				<td>${p.companyName}</td>
				<td><a href="Customers?action=edit&id=${p.id}">Edit</a></td>
				<td><a href="Customers?action=delete&id=${p.id}">Delete</a></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>