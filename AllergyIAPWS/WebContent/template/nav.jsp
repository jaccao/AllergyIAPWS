<%@page import="com.allergyiap.beans.Customer"%>
<%
	//allow access only if session exists
	Customer user = (Customer) session.getAttribute("User");
	String userName = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				userName = cookie.getValue();
			if (cookie.getName().equals("JSESSIONID"))
				sessionID = cookie.getValue();
		}
	}
%>

<ul class="nav navbar-nav navbar-antisida">
	<li class=""><a href="ProductCatalog"> <span
			class="glyphicon glyphicon-th-list"></span> Products
	</a></li>
	<%
		if (!user.isAdmin()) {
	%>
		<li class=""><a href="Relations?idcustomer=1"> <span class="fa fa-medkit"></span>
			Manage pharmacies relations
	</a></li>
	<%
		}
	%>

	<%
		if (user.isAdmin()) {
	%>
	<li class=""><a href="Customers"> <span class="fa fa-users"></span>
			Customers
	</a></li>
	<li class=""><a href="XarxaImportServlet"> 
		<span class="fa fa-cloud-download"></span> Import Allergies
	</a></li>
	<li class=""><a href="Users"> <span class="fa fa-users"></span>
			Users
	</a></li>
	<li class=""><a href="Pharmacies"> <span
			class="glyphicon glyphicon-shopping-cart"></span> Pharmacies
	</a></li>
	<%
		}
	%>
</ul>

<ul class="nav navbar-nav navbar-antisida navbar-right">
	<li class="dropdown"><a href="#" class="dropdown-toggle"
		data-toggle="dropdown" role="button" aria-haspopup="true"
		aria-expanded="false"> <i class="fa fa-user fa-lg"></i> <%
 	out.print(user.getUser_mail());
 %> <span class="caret"></span>

	</a>
		<ul class="dropdown-menu">
			<li><a href="LogoutServlet"> <i class="fa fa-sign-out"
					style="color: red"></i> Logout
			</a></li>
		</ul>
		
	</li>
</ul>