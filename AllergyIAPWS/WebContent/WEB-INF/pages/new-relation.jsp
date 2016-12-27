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
					<h2>New pharmacy relation for customer with ID ${idcustomer}</h2>
			        <div class="panel panel-default">
			            <div class="panel-heading text-left"></div>
			            <div class="panel-body">
			                <form id="form-centers" action="Relations?action=new&idcustomer=${idcustomer}" method="POST" role="form" >
			                    	<div class="row">
			                    	<div class="col-md-6">
					                    <!-- Posible pharmacies -->
					                    <div class="form-group btn-xs">
					                    	<select class="form-control" id="pharmacy" name="pharmacy">
					                    	<c:forEach items="${pharmacies}" var="p">
					                    		<option value="${p.id_pharmacy}">${p.name_pharmacy}</option>
					                    	</c:forEach>
					                    	</select>
					                    </div>
									</div>
									</div>
			                    <div class="form-group btn-xs text-center" style="margin-top: 3%;">
			                        <button type="submit" class="btn btn-success" data-loading-text="Save <i class='fa fa-spinner fa-spin'></i>" style="width: 40%">
			                            Save
			                        </button>
			                    </div>
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