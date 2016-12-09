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

				<div class="col-sm-8 col-sm-offset-2">
					<h2>New Product</h2>
			        <div class="panel panel-default">
			            <div class="panel-heading text-left">New Product</div>
			            <div class="panel-body">
			
			                <form id="form-centers" action="ProductCatalog?action=new" method="POST" role="form">
			                   
			                    <div class="row">
			                    	<div class="col-md-6">
					                    <!-- Name -->
					                    <div class="form-group btn-xs">
					                        <input type="text" class="form-control" id="name" name="name" placeholder="Name" required>
					                    </div>
					
									</div>		
									<div class="col-md-6">
					                    <!-- Allergy -->
					                    <div class="form-group btn-xs">
					                    	<select class="form-control" id="allergy" name="allergy">
					                    	<c:forEach items="${alergies}" var="allergy">
					                    		<option value="${allergy.id}">${allergy.allergyName}</option>
					                    	</c:forEach>
					                    	</select>
					                    </div>
				
									</div>	  
									<div class="col-md-6">                  
										<!-- Description -->
					                    <div class="form-group btn-xs">
					                    	<textarea class="form-control" id="description" name="description" maxlength="100" rows="2" aria-describedby="iconbody" placeholder="Description"></textarea>
					                    </div>
									</div>
									
									<% if(user.isAdmin()){ %>
				                    </div>		
										<div class="col-md-6">
						                    <!-- User Id --> 
						                    <div class="form-group btn-xs">
						                        Select the customer ID
						                    	<select class="form-control" id="customer" name="customer">
						                    	<c:forEach items="${customers}" var="customer">
						                    		<option value="${customer.id}">ID (${customer.id}) - ${customer.userName}</option>
						                    	</c:forEach>
						                    	</select>
						                    </div>
										</div>
									<%} %>
									
									<div class="col-md-6">
										Image Upload:<input type="file" name="fileName">
									</div>
								</div>
			                    <div class="form-group btn-xs text-center" style="margin-top: 3%;">
			                        <button type="submit" class="btn btn-success" data-loading-text="Save <i class='fa fa-spinner fa-spin'></i>" style="width: 40%">
			                            Save
			                        </button>
			                    </div>
			                </form>
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