<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Product</title>
</head>
<body>
	<h2>New Product</title>
	<form action="ProductCatalog?action=new" method="POST">
		<label>Nombre</label> 
		<input type="text" name="name"> <br /> 
		<label>Allergy</label>
		<input type="text" name="allergy"> <br /> 
		<label>Customer</label>
		<input type="text" name="customer"> <br /> 
		<label>Description</label>
		<input type="text" name="description">
		
		<br/>
		
		<button type="submit">Save</button>
	</form>
</body>
</html>