<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file='/template/html-head.jsp'%>
<body>
	<div class="container">
		<header class="row"> <%@include file='/template/header.jsp'%>
		</header>
		<div id="main" class="row">
			<div class="container-fluid">
			<div class="col-sm-10 col-sm-offset-1">
				<h2>Pharmacies</h2>
				<div class="panel panel-default">
					<div class="panel-heading text-right">
						<a class="btn btn-success" href="Relations?action=new">
							<i class="fa fa-plus"></i> 
						</a>
					</div>
					<div class="panel-body">
	
						<table class="table">
						</table>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
</body>
<%@include file='/template/html-script.jsp'%>
</html>