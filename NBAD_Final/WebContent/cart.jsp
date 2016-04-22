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
				<form action="OrderController" method="post">
					<b style="font-size: 25px; font-weight: bolder;">Your Cart</b><br>
					<br>

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

						<% Cart cart = (Cart)request.getSession(true).getAttribute("theShoppingCart");
           if(cart!=null){
               int i=1;
               for(OrderItem item : cart.getItems()){
                   Product product= item.getProduct();
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
							<td></td>
							<td></td>
							<td class="mytableheader">subtotal</td>
							<td><%=cart.getCartAmount()%></td>
						</tr>

					</table>
					<input type="hidden" name="action" value="updateCart" /> <input
						class="mybutton" type="submit" value="UpdateCart"><br>
				</form>

				<form action="OrderController" method="get">
					<input type="hidden" name="action" value="checkOut" /> <input
						class="mybutton" type="submit" value="Checkout"
						style="margin-top: 10px">
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
