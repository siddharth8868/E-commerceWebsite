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
	   	 	if(request.getAttribute("error")==null){
	   		} 
	   	 	else{
		   		error=(String)request.getAttribute("error");
	   	 	}
		%>
			
			<div class="centerbody2">
				<form action="UserController" method="post">
				<input type="hidden" name="access" value="signup">
				<input type="hidden" name="loginType" value="<%=(String)request.getSession(true).getAttribute("loginType")%>">
				<strong style="font-weight: bold;">Sign-UP</strong><br>
				<table>
				<tr>
				<td>FirstName</td>
				<td><input type="text" name="firstName"></td>
				</tr>
				<tr>
				<td>LastName</td>
				<td><input type="text" name="lastName"></td>
				</tr>
				<tr>
				<td>EmailAddress</td>
				<td><input type="text" name="emailAddr">
				</tr>
				<tr>
				<td>password</td>
				<td><input type="password" name="password"></td>
				</tr>
				<tr>
				<td>re-password</td>
				<td><input type="password" name="re-password"></td>
				</tr>
				<tr>
				<td>Address Field 1</td>
				<td><input type="text" name="address1Field"></td>
				</tr>
				<tr>
				<td>Address Field 2</td>
				<td><input type="text" name="address2Field"></td>
				</tr>
				<tr>
				<td>City</td>
				<td><input type="text" name="city"></td>
				</tr>
				<tr>
				<td>State</td>
				<td><input type="text" name="state"></td>
				</tr>
				<tr>
				<td>Zip code</td>
				<td><input type="text" name="zipPostalCode"></td>
				</tr>
				<tr>
				<td>country</td>
				<td><input type="text" name="country"></td>
				</tr>
				</table>
				<span style="color: red;"><%=error %></span><br>
				<input type="submit" value="Sign In">
				</form>
			</div>
			</div>		
	</div>
</body>

</html>
