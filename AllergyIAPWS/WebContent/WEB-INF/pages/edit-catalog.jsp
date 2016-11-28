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
			
			                <form id="form-centers" action="ProductCatalog?action=edit&id=${p.id}" method="POST" role="form" >
			                    
			                    <div class="row">
			                    	<div class="col-md-6">
					                    <!-- Name -->
					                    <div class="form-group btn-xs">
					                        <input type="text" class="form-control" id="name" name="name" value="${p.productName}" placeholder="Name" required>
					                    </div>
					
									</div>		
									<div class="col-md-6">
					                    <!-- Allergy -->
					                    <div class="form-group btn-xs">
					                    	<select class="form-control" id="allergy" name="allergy">
					                    	<c:forEach items="${alergies}" var="allergy">
					                    		<c:choose>
					                    		<c:when test="${allergy.id == p.allergyId }">
					                    			<option value="${allergy.id}" selected>${allergy.allergyName}</option>
					                    		</c:when>
					                    		<c:otherwise>
					                    			<option value="${allergy.id}" >${allergy.allergyName}</option>
					                    		</c:otherwise>
					                    		</c:choose>
					                    	</c:forEach>
					                    	</select>
					                        <!--input type="text" class="form-control" id="allergy" name="allergy" value="${p.allergyId}" placeholder="Allergy" required-->
					                    </div>
				
									</div>	  
									<div class="col-md-6">                  
										<!-- Description -->
					                    <div class="form-group btn-xs">
					                    	<textarea class="form-control" id="description" name="description" maxlength="100" rows="2" aria-describedby="iconbody" placeholder="Description">${p.productDescription}</textarea>
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