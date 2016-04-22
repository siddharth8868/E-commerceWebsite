<%-- 
    Document   : header
    Created on : Feb 22, 2015, 7:28:41 PM
    Author     : siddharth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" property="stylesheet" type="text/css"
	href="css/main.css" />
	
	<%  HttpSession hts= request.getSession(true);
		String user = (String)hts.getAttribute("firstName");
		if(user==null){
			user = "not logged in";
		}
	%>
	
<div class="myheader">
	<div class="logindiv"><%=user %></div>
	<div class="logo">
		<img height="100" width="100" src="images/logo.jpg"
			alt="unable to load image">Best Seller
	</div>

	<% String breadcrumb = (String)request.getAttribute("breadcrumb"); %>

	<div class="breadcrumb"><%=breadcrumb%></div>
</div>

