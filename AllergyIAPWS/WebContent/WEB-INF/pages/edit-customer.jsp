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
					<h2>Edit Customer</h2>
			        <div class="panel panel-default">
			            <div class="panel-heading text-left">Edit Customer</div>
			            <div class="panel-body">
			
			                <form id="form-centers" action="Customers?action=edit&id=${c.id}" method="POST" role="form" >
			                    
			                    <div class="row">
			                    	<div class="col-md-6">
					                    <!-- Name -->
					                    <div class="form-group btn-xs">
					                        <input type="text" class="form-control" id="nUserName" name="nUserName" value="${c.userName}" placeholder="User Name">
					                    </div>
					
									</div>		
									<div class="col-md-6">
					                    <!-- Password -->
					                    <div class="form-group btn-xs">
					                        <input type="password" class="form-control" id="nUserPassword" name="nUserPassword" value="${c.password}" placeholder="Password">
					                    </div>
				
									</div>	  
									<div class="col-md-6">                  
										<!-- Company Name -->
					                    <div class="form-group btn-xs">
					                    	<input type="text" class="form-control" id="nCompanyName" name="nCompanyName" value="${c.companyName}" placeholder="Company Name">
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