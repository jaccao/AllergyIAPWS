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
					<h2>Edit User</h2>
			        <div class="panel panel-default">
			            <div class="panel-heading text-left">Edit User</div>
			            <div class="panel-body">
			
			                <form id="form-centers" action="Users?action=edit&id=${u.iduser}" method="POST" role="form" >
			                    
			                    <div class="row">
			                    	<div class="col-md-6">
					                    <!-- Name -->
					                    <div class="form-group btn-xs">
					                        <input type="text" class="form-control" id="name" name="name" value="${u.user_name}" placeholder="User Name" required>
					                    </div>
					                </div>
					                 
					                <div class="col-md-6">
					                    <!-- Second Name -->
					                    <div class="form-group btn-xs">
					                        <input type="text" class="form-control" id="secondName" name="secondName" value="${u.user_second_name}" placeholder="User Second Name" required>
					                    </div>
					                    
									</div>
									
									<div class="col-md-6">
					                    <!-- User Mail -->
					                    <div class="form-group btn-xs">
					                        <input type="text" class="form-control" id="mail" name="mail" value="${u.user_mail}" placeholder="Mail" required>
					                    </div>
					                </div>
											
									<div class="col-md-6">
					                    <!-- User Password -->
					                    <div class="form-group btn-xs">
					                        <input type="password" class="form-control" id="password" name="password" value="${u.user_password}" placeholder="Password" required>
					                    </div>
				
									</div>	
									<div class="col-md-6">
					                    <!-- Location default -->
					                    <div class="form-group btn-xs">
					                    	<select class="form-control" id="location" name="location">
					                    	<c:forEach items="${locations}" var="loc">
					                    		<c:choose>
					                    		<c:when test="${loc.name_station == u.user_station_default}">
					                    			<option value="${loc.name_station}" selected>${loc.name_station}</option>
					                    		</c:when>
					                    		<c:otherwise>
					                    			<option value="${loc.name_station}" >${loc.name_station}</option>
					                    		</c:otherwise>
					                    		</c:choose>
					                    	</c:forEach>
					                    	</select>
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