
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Utility.ProductDB"%>
<%@page import="beans.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>catalog</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/navigation.css" />
</head>
<body class="mybody">
	<div class="aligncenter">

		<% request.setAttribute("breadcrumb","<a href=\"index.jsp\">Home</a> > <a href=\"catalog.jsp\">Catalog</a>"); %>

		<!-- header -->
		<jsp:include page="/includes/header.jsp" />

		<!-- user-navigation -->
		<jsp:include page="/includes/user-navigation.jsp" />

		<%   String catalogCategory = (String)request.getAttribute("catalogCategory");
    if(catalogCategory==null){
    catalogCategory = "All";
    }
    
    ProductDB productDB= new ProductDB();
    ArrayList<Product> products = new ArrayList<Product>();
    Set<String> list= new HashSet<String>();
    int i=0;
    for(Product product : productDB.getProducts()){
        String temp=product.getCatalogCategory();
        list.add(temp);
        if(temp.equals(catalogCategory)|| catalogCategory.equals("All")){
            products.add(product);
        }
    }
    
%>




		<div class="centerbody">
			<div class="centerbody1">
				<!-- site navigation -->
				<jsp:include page="includes/site-navigation.jsp" />
			</div>

			<div class="centerbody2">
				<form action="CatalogController" method="GET">
					Category: <select name="catalogCategory">
						<option <%if(catalogCategory.equals("All")){ %> selected <% }%>
							value="All">All</option>
						<%
                    for(String val : list){
                        %><option <%if(catalogCategory.equals(val)){ %>
							selected <% }%> value="<%=val%>"><%=val%></option>
						<%
                    }
                    %>
					</select> <input type="submit" name="" value="search" />
					<table style="width: 100%">
						<tr class="mytableheader">
							<td>Image</td>
							<td>product</td>
							<td>category</td>
							<td>price</td>
						</tr>
						<tr style="color: #00b7b7">
							<td><hr></td>
							<td><hr></td>
							<td><hr></td>
							<td><hr></td>
						</tr>
						<%
                                  for(Product product : products){
                                  %>
						<tr>
							<td><img src="<%=product.getGetImageURL()%>" alt="no image"
								width="100" height="100"></td>
							<td><a
								href="CatalogController?productCode=<%=product.getProductCode()%>"><%=product.getProductName()%></a></td>
							<td><%=product.getCatalogCategory()%></td>
							<td><%=product.getPrice()%></td>
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
