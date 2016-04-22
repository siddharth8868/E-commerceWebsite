<%@page import="java.text.SimpleDateFormat"%>
<%@page import="beans.Order"%>
<%@page import="java.util.ArrayList"%>
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
<script type="text/javascript" src="js/viewOrder.js"></script>
</head>
<body class="mybody">
	<div class="aligncenter">

		<% request.setAttribute("breadcrumb","<a href=\"index.jsp\">Home</a> > <a href=\"OrderController?action=viewOrders\" class=\"active\" >My Orders</a>");%>
		
		<!-- header -->
		<jsp:include page="/includes/header.jsp" />


		<% request.setAttribute("ordersList","ordersList"); %>
		
		<!-- user-navigation -->
		<jsp:include page="/includes/user-navigation.jsp" />

		<div class="centerbody">
			<div class="centerbody1">
				<!-- site navigation -->
				<jsp:include page="includes/site-navigation.jsp" />
			</div>



			<div class="centerbody2">
				<%
					HttpSession hts = request.getSession(true);
					ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("orders");
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					float total;
				%>
				<p>Orders</p>
				<form id="myForm" action="OrderController" method="post">
				<input type="hidden" name="action" value="viewOrder">
				<input type="hidden" name="orderId" id="orderId" value="">
				<table>
					<tr>
					<th>Order Number</th>
					<th>Customer</th>
					<th>Order Date</th>
					<th>Total</th>
					</tr>
					
					<%
					for(Order order: orders){
						total=order.getTotalCost()+(order.getTotalCost()*order.getTaxRate());
						%>
						<tr>
						<td><a href="#" onclick="validate(<%=order.getOrderNumber()%>);"><%=order.getOrderNumber() %></a></td>
						<td><%=(String)hts.getAttribute("firstName")%></td>
						<td><%=sdf.format(order.getDate())%></td>
						<td><%=total %></td>
						</tr>
						<%
					}
					%>
					</table>
				</form>
			</div>
		</div>

		<!--footer -->
		<jsp:include page="/includes/footer.jsp" />

	</div>
</body>

</html>
