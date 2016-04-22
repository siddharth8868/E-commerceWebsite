
<%@page import="beans.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>item</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/navigation.css" />
</head>
<body class="mybody">
	<div class="aligncenter">

		<% request.setAttribute("breadcrumb","<a href=\"index.jsp\">Home</a> > <a href=\"catalog.jsp\">Catalog</a> > <a href=\"item.jsp\">Item</a>");
   Product product = (Product)request.getAttribute("product");
   
   
           
%>

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

				<table style="width: 50%">
					<tr>
						<td><img src="<%=product.getGetImageURL()%>" height="200"
							width="200" alt="no image"></td>
						<td><span
							style="font-size: 30px; font: bold; font-weight: 400;"><%=product.getProductName()%></span>
							<br><%=product.getCatalogCategory()%> <br><%=product.getPrice()%>
						</td>
						<td><span
							style="font-size: 30px; font: bold; font-weight: 300; color: blue"><a
								href="catalog.jsp">back</a></span></td>
					</tr>
				</table>

				<form action="OrderController" method="GET">
					<input type="hidden" name="productList"
						value="<%=product.getProductCode()%>" /> <input type="hidden"
						name="<%=product.getProductCode()%>" value="1" /> <input
						type="hidden" name="action" value="updateCart" /> <input
						type="hidden" name="itempage" value="true" /> <input type="submit"
						value="Add to Cart" style="color: blue"><br>
					<br> <b> Specifications</b>

					<%=product.getDescription()%>

				</form>
			</div>
		</div>

		<!--footer -->
		<jsp:include page="/includes/footer.jsp" />

	</div>
</body>
</html>
