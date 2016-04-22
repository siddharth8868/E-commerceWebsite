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



			<div class="centerbody2">
				<%
					HttpSession hts = request.getSession(true);
					ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("orders");
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					float total;
				%>
				<p>Orders</p>
				
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
						<td><%=order.getOrderNumber() %></td>
						<td><%=(String)hts.getAttribute("firstName")%></td>
						<td><%=sdf.format(order.getDate()) %></td>
						<td><%=total %></td>
						</tr>
						<%
					}
					%>
				</table>
			</div>
		</div>

		<!--footer -->
		<jsp:include page="/includes/footer.jsp" />

	</div>
</body>

</html>
