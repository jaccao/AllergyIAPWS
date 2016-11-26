<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product</title>
</head>
<body>
	<h2>Edit Product</h2>
	<form action="ProductCatalog?action=edit&id=${p.id}" method="POST">
		<label>Nombre</label> 
		<input type="text" name="name" value="${p.productName}"> <br /> 
		<label>Allergy</label>
		<input type="text" name="allergy" value="${p.allergyId}"> <br /> 
		<label>Customer</label>
		<input type="text" name="customer" value="${p.customerId}"> <br /> 
		<label>Description</label>
		<input type="text" name="description" value="${p.productDescription}">
		
		<br/>
		
		<button type="submit">Edit</button>
	</form>
	
</body>
</html>