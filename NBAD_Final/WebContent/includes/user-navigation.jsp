<%-- 
    Document   : user-navigation
    Created on : Feb 22, 2015, 7:29:22 PM
    Author     : siddharth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String val=(String)request.getRequestURI(); %>

<div id="navigation">
	<ul>
		<li><a href="checkoutChoice.jsp"
			<% 
            
           if(val.endsWith("checkoutChoice.jsp")){
           %>
			class="active" <%
           }
            
            %>>Sign
				In</a></li>
		<li><a href="cart.jsp"
			<% 
            if(val.endsWith("cart.jsp") || val.endsWith("order.jsp")){
            %>
			class="active" <%
            }
            %>>Cart</a></li>
		<li><a href="OrderController?action=viewOrders"
			<% 
           if(request.getAttribute("ordersList")!=null && request.getAttribute("ordersList").equals("ordersList")){
           %>
			class="active" <%
           }
            
            %>>My
				Orders</a></li>
	</ul>
</div>