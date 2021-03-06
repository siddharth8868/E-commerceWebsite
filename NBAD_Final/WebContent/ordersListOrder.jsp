
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="beans.Product"%>
<%@page import="beans.OrderItem"%>
<%@page import="beans.Cart"%>
<%@page import="beans.User"%>
<%@page import="beans.Order"%>
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

		<%
			request.setAttribute(
					"breadcrumb",
					"<a href=\"index.jsp\">Home</a> > <a href=\"OrderController?action=viewOrders\" class=\"active\" >My Orders</a> > <a href=\"#\">Order</a>");
		%>

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

			<%
				HttpSession hts = request.getSession();
				Order order = (Order) request.getAttribute("orders");
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				String sDate = sdf.format(date);
				User user = (User) hts.getAttribute("theUser");
			%>

			<div class="centerbody2">
				<form action="cart.jsp">

						<p>Order Number: <%=order.getOrderNumber() %></p>

						<table id="carttable">
						<tr class="mytableheader">
							<td class="seventy">Item</td>
							<td class="ten">Price</td>
							<td class="ten">Quantity</td>
							<td class="ten">Total</td>
						</tr>

						<tr>
							<td style="color: #00b7b7"><hr></td>
							<td><hr></td>
							<td><hr></td>
							<td><hr></td>
						</tr>


						<%

							if (order != null) {
								int i = 1;
								for (OrderItem item : order.getOrderItem()) {
									Product product = item.getProduct();
						%>
						<tr>
							<td><%=product.getProductName()%></td>
							<td><%=product.getPrice()%></td>
							<td><input type="text" style="width: 20px;"
								name="<%=product.getProductCode()%>"
								value="<%=item.getQuantity()%>"></td>
							<td><%=item.getTotal()%></td>

						</tr>
						<tr>
							<td><input type="hidden" name="productList"
								value="<%=product.getProductCode()%>" />
							<td>
							<td></td>
							<td></td>
						</tr>

						<%
							}
						%>


						<tr>
							<td style="color: #00b7b7"><hr></td>
							<td><hr></td>
							<td><hr></td>
							<td><hr></td>
						</tr>
						<%
							float subtotal, tax, total;
								subtotal = order.getTotalCost();
								tax = order.getTaxRate() * subtotal;
								total = subtotal + tax;
						%>
						<tr>
							<td></td>
							<td></td>
							<td class="mytableheader">subtotal</td>
							<td><%=subtotal%></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td class="mytableheader">tax</td>
							<td><%=tax%></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td class="mytableheader">total</td>
							<td><%=total%></td>
						</tr>

					</table>

				</form>

				<%
					}
				%>

				
			</div>
		</div>

		<!--footer -->
		<jsp:include page="/includes/footer.jsp" />

	</div>
</body>

</html>
