<%-- 
    Document   : site-navigation
    Created on : Feb 22, 2015, 7:29:38 PM
    Author     : siddharth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" property="stylesheet" type="text/css"
	href="css/navigation.css" />

<% String val=(String)request.getRequestURI(); %>

<a href="index.jsp"
	<% 
            
           if(val.endsWith("index.jsp")){
               %>
	style="color: #c0c0c0" <%
           }
            
            %>>Home</a>
<br>
<br>
<a href="catalog.jsp"
	<% 
            
           if(val.endsWith("catalog.jsp")){
           %>
	style="color: #c0c0c0" <%
           }
            
            %>>Catalog</a>
<br>
<br>
<a href="about.jsp"
	<% 
            
           if(val.endsWith("about.jsp")){
           %>
	style="color: #c0c0c0" <%
           }
            
            %>>About</a>
<br>
<br>
<a href="contact.jsp"
	<% 
            
           if(val.endsWith("contact.jsp")){
           %>
	style="color: #c0c0c0" <%
           }
            
            %>>Contact
	Us</a>