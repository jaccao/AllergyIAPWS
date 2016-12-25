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
				<h2>Customers</h2>
				<div class="panel panel-default">
					<div class="panel-heading text-right">
						<a class="btn btn-success" href="Customers?action=new">
							<i class="fa fa-plus"></i> New Customer
						</a>
					</div>
					<div class="panel-body">
	
						<table class="table">
							<thead>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Password</th>
									<th>Company</th>
									<th style="width: 15%"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${customers}" var="p">
									<tr>
										<td>${p.idcustomer}</td>
										<td>${p.user_mail}</td>
										<td>${p.user_password}</td>
										<td>${p.company_name}</td>
										<td>
											<a class="btn btn-warning" href="Customers?action=edit&idcustomer=${p.idcustomer}"> 
												<i class="fa fa-pencil"></i>
											</a>
									
											<a class="btn btn-danger" href="Customers?action=delete&idcustomer=${p.idcustomer}">
												<i class="fa fa-trash-o"></i>
											</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			</div>
		</div>
		<footer class="row"> <%@include file='/template/footer.jsp'%>
		</footer>
	</div>
</body>
<%@include file='/template/html-script.jsp'%>
</html>