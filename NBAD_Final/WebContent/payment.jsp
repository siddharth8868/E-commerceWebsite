<%@page import="beans.Order"%>
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
			<br>
			<span style="font-size: 24px; font-weight: 700;" >Enter Your Payment Information</span>
			<hr style="font-size: 24px; font-weight: 700; color: aqua;"/>


		<%
			HttpSession hts = request.getSession(true);
			Order order =(Order)hts.getAttribute("currentOrder");
			if(order!=null){
				
		%>

			<form action="OrderController" method="post">
				<input type="hidden" name="action" value="confirmOrder"/>
				<table>
				<tr>
				<td>Credit Card Type</td>
				<td>
				<select name="cardType">
				<option value="Visa">Visa</option>
				<option value="Master">Master</option>
				<option value="American Express">American Express</option>
				<option value="Discover">Discover</option>
				</select>
				</td>
				</tr>
				<tr>
				<td>Card Number</td>
				<td><input name="cardName" type="text" style="width: 130px"/></td>
				</tr>
				<tr>
				<td>Exipration Date</td>
				<td>
				<select name="expMonth" >
				<%
				for(int i=1; i<13;i++){
					%>
						<option value="<%=i%>"> <%=i%> </option>	
					<%
				}
				%>
				</select>
				<select name="expYear" >
				<%
				for(int i=2015; i<2026;i++){
					%>
						<option value="<%=i%>"> <%=i%> </option>	
					<%
				}
				%>
				</select>
				</td>
				</tr>
				<tr>
				<td>CVV</td>
				<td>
				<input name="cvv" type="text" style="width: 40px" />
				</td>
				</tr>
				</table>
					<hr style="font-size: 24px; font-weight: 700; color: aqua;"/>
					<p>your card will be charged a total of: <span style="color: red;">$<%=order.getTotalCost() +(order.getTotalCost() * order.getTaxRate()) %></span></p>
				<input class="mybutton" type="submit" value="confirm payment">
			</form>
			
			<%
				}
				else{
					%>
					<p>sorry,your session expired</p>
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
