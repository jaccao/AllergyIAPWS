<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Hello world
	<%
	String strStatus = "testando";
	try {
		InitialContext cxt = new InitialContext();
		if (cxt != null) {
			DataSource ds = (DataSource) cxt.lookup("java:/PostgresXADS");
			if (ds == null)
				strStatus = "Error while obtaining the dataSource";
			else {
				Connection connection = ds.getConnection();
				Statement stm = connection.createStatement();
				//stm.executeUpdate("insert into example (code, name) values('123','456')");
				connection.close();
				stm.close();
			}
		}
	} catch (Exception e) {
		strStatus = e.getMessage();
	}
	out.println(strStatus);
%>
</body>
</html>