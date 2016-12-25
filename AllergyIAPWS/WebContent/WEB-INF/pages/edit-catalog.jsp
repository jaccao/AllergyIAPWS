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
					<h2>Edit Product</h2>
			        <div class="panel panel-default">
			            <div class="panel-heading text-left">&nbsp;</div>
			            <div class="panel-body">
			
			                <form id="form-centers" action="ProductCatalog?action=edit&id=${p.idproduct_catalog}" method="POST" role="form" >
			                    
			                    <div class="row">
			                    	<div class="col-md-6">
					                    <!-- Name -->
					                    <div class="form-group btn-xs">
					                        <input type="text" class="form-control" id="name" name="name" value="${p.product_name}" placeholder="Name" required>
					                    </div>
					
									</div>		
									<div class="col-md-6">
					                    <!-- Allergy -->
					                    <div class="form-group btn-xs">
					                    	<select class="form-control" id="allergy" name="allergy">
					                    	<c:forEach items="${alergies}" var="allergy">
					                    		<c:choose>
					                    		<c:when test="${allergy.idallergy == p.allergy_idallergy}">
					                    			<option value="${allergy.idallergy}" selected>${allergy.allergy_name}</option>
					                    		</c:when>
					                    		<c:otherwise>
					                    			<option value="${allergy.idallergy}" >${allergy.allergy_name}</option>
					                    		</c:otherwise>
					                    		</c:choose>
					                    	</c:forEach>
					                    	</select>
					                        <!--input type="text" class="form-control" id="allergy" name="allergy" value="${p.allergy_idallergy}" placeholder="Allergy" required-->
					                    </div>
					              	</div>
					             </div>
					             	<div class="row">
						             <div class="col-md-6">                  
											<!-- Description -->
						                    <div class="form-group btn-xs">
						                    	<textarea class="form-control" id="description" name="description" maxlength="100" rows="2" aria-describedby="iconbody" placeholder="Description">${p.product_description}</textarea>
						                    </div>
									</div>
					                    <% if(user.isAdmin()){ %>
					                    <div class="col-md-6">
					                    <!-- Customer ID -->
					                    <div class="form-group btn-xs">
					                    	Select the customer ID
					                    	<select class="form-control" id="customer" name="customer">
					                    	<c:forEach items="${customers}" var="customer">
					                    		<c:choose>
					                    		<c:when test="${customer.idcustomer == p.customer_idcustomer }">
					                    			<option value="${customer.idcustomer}" selected>ID (${customer.idcustomer}) - ${customer.user_mail}</option>
					                    		</c:when>
					                    		<c:otherwise>
					                    			<option value="${customer.idcustomer}">ID (${customer.idcustomer}) - ${customer.user_mail}</option>
					                    		</c:otherwise>
					                    		</c:choose>
					                    	</c:forEach>
					                    	</select>
					                    </div>
										</div>
										<%} %>
									<div class="col-md-12">                  
										<!-- Image URL -->
										<div class="input-group">
  											<span class="input-group-addon" id="basic-addon3">https://example.com/users/</span>
  											<textarea type="text" class="form-control" style="resize:none" placeholder="Image URL" id="url" name="url" rows="1" aria-describedby="basic-addon3">${p.product_url_image}</textarea>
										</div>
					                    </div>
									</div>
									</div>	 
									</div>		
									
			                    <div class="form-group btn-xs text-center" style="margin-top: 3%;">
			                        <button type="submit" class="btn btn-success" data-loading-text="Save <i class='fa fa-spinner fa-spin'></i>" style="width: 40%">
			                            Edit
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