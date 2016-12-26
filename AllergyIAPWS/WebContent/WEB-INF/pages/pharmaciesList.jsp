<%@page import="com.allergy.service.RelationPharmaciesCustomersService"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file='/template/html-head.jsp'%>
<%@ page import="java.util.List" %>
<%@ page import="com.allergyiap.beans.RelationPharmaciesCustomers" %>


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
						<a class="btn btn-success" href="Pharmacies?action=new">
							<i class="fa fa-plus"></i> New Pharmacy
						</a>
					</div>
					<div class="panel-body">
	
						<table class="table">
							<thead>
								<tr>
									<th>Id Pharmacy</th>
									<th>Name</th>
									<th>Latitude</th>
									<th>Longitude</th>
									<th>Customers</th>
									<th style="width: 15%"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pharmacies}" var="p">
									<tr>
										<td>${p.key.id_pharmacy}</td>
										<td>${p.key.name_pharmacy}</td>
										<td>${p.key.latitude}</td>
										<td>${p.key.longitude}</td>
										<td>
											<div class="dropdown">
											    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Customers
											    <span class="caret"></span></button>
											    <ul class="dropdown-menu">
											    <c:forEach items="${p.value}" var="entry">
    												<li><a href="#">Customer ID: ${entry}</a></li>
												</c:forEach>
											    </ul>
											 </div>
										</td>
										<td>
											<a class="btn btn-warning" href="Pharmacies?action=edit&id_pharmacy=${p.key.id_pharmacy}"> 
												<i class="fa fa-pencil"></i>
											</a>
									
											<a class="btn btn-danger" href="Pharmacies?action=delete&id_pharmacy=${p.key.id_pharmacy}">
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