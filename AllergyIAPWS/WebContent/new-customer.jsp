<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Customer</title>
</head>
<body>
<h2>New Customer</h2>
	<form action="Customers?action=new" method="POST">
		<label>Name</label> 
		<input type="text" name="userName"> <br /> 
		<label>Password</label>
		<input type="text" name="userPassword"> <br /> 
		<label>Company</label>
		<input type="text" name="companyName"> 
		<br/>
		
		<button type="submit">Save</button>
	</form>

</body>
</html>