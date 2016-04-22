<%@page import="beans.Product"%>
<%@page import="beans.OrderItem"%>
<%@page import="beans.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkout Choice</title>
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
			<p><span style="font-size: 28px; font-weight: bold;" >Login or Create User</span></p>
			<p><span style="font-size: 18px;" >select one of the two options below to proceed checkout</span></p><br>
			
			<%
			String loginType=(String)request.getSession(true).getAttribute("loginType");
			System.out.print(loginType);
			if(loginType==null){
					request.getSession().setAttribute("loginType","normal");
				}
			%>
			
			
			<form action="login.jsp">
			<input type="submit" class="mybutton" value="Login as Existing User"/>
			</form>
			<form action="userInfo.jsp">
			<input type="submit" class="mybutton" value="Create New User"/>
			</form>	
			</div>
		</div>

		<!--footer -->
		<jsp:include page="/includes/footer.jsp" />

	</div>
</body>

</html>
