<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Customer</title>
</head>
<body>
<h2>Edit Product</h2>
	<form action="Customers?action=edit&id=${c.id}" method="POST">
		<label>Name</label> 
		<input type="text" name="userName" value="${c.userName}"> <br /> 
		<label>Password</label>
		<input type="text" name="userPassword" value="${c.userPassword}"> <br /> 
		<label>Company</label>
		<input type="text" name="companyName" value="${c.companyName}">
		<br/>
		
		<button type="submit">Edit</button>
	</form>

</body>
</html>