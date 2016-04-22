<%@page import="beans.Product"%>
<%@page import="beans.OrderItem"%>
<%@page import="beans.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cart</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/navigation.css" />
</head>
<body class="mybody">
	<div class="aligncenter">

		<% request.setAttribute("breadcrumb","<a href=\"index.jsp\">Home</a> > <a href=\"catalog.jsp\">Catalog</a> > <a href=\"cart.jsp\">Cart</a>"); %>

		<!-- header -->
		<jsp:include page="/includes/header.jsp" />

		<!-- user-navigation -->
		<jsp:include page="/includes/user-navigation.jsp" />

		<div class="centerbody">
			<div class="centerbody1">
				<!-- site navigation -->
				<jsp:include page="includes/site-navigation.jsp" />
			</div>

	<% String error="";
	   if(request.getAttribute("error")!=null){
		   error=(String)request.getAttribute("error");
	   } 
	   
		
		if(request.getSession(true).getAttribute("loginType")==null){
			request.getSession(true).setAttribute("loginType","normal");
		}
	%>

			<div class="centerbody2">
				<form action="UserController" method="post">
				<input type="hidden" name="access" value="login">
				<strong style="font-weight: bold;">Login</strong><br>
				Username:<input type="text" name="username"><br>
				Password:<input type="password" name="password"><%=error%><br>
				<input type="submit" value="login">
				</form>
			</div>
			</div>		
	</div>
</body>

</html>
