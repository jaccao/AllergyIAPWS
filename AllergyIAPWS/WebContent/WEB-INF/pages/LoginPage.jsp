<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@include file='/template/html-head.jsp'%>

<body class="login">
	<div class="container">
		<div id="main" class="row">
			<div class="container-fluid">
			
				<div class="col-sm-4 col-sm-offset-4">
			        <div class="panel panel-default">
			            
			            <div class="panel-body">
			                <form id="frm-login" class="form-horizontal " role="form" action="LoginServlet" method="post">

								<% String error = (String) session.getAttribute("error");
									if(error != null  && !error.isEmpty()) {%>
										<div class="alert alert-danger">${error}</div>
								<%} %>
			                    
			                    <div class="form-group btn-xs">
			                        <div class="input-group">
			                            <span class="input-group-addon" id="iconmail"><i class="fa fa-user"></i></span>
			                            <input type="text" class="form-control" name="user_name" value="" placeholder="Name" aria-describedby="iconmail">
			                        </div>
			                    </div>
			                    <div class="form-group btn-xs">
			                        <div class="input-group">
			                            <span class="input-group-addon" id="iconkey"><i class="fa fa-key"></i></span>
			                            <input type="password" class="form-control" name="user_password" placeholder="Password" aria-describedby="iconkey">
			                        </div>
			                    </div>
			                    <div class="form-group btn-xs">
			                        <div class="input-group" style="float: right">
			                            <button type="submit" class="btn btn-success" data-loading-text="Login <i class='fa fa-spinner fa-spin'></i>">
			                                Login
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