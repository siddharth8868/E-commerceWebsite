<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/navigation.css" />
</head>
<body class="mybody">
	<div class="aligncenter">

		<% request.setAttribute("breadcrumb","<a href=\"index.jsp\">Home</a>"); %>


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
				<p>The best selling phones and tablets in the web. This site
					will give you the best search mechanisms.</p>
				<p>This provides tracking ID of your orders.Will provide a
					secured one to access your account</p>
			</div>
		</div>

		<!--footer -->
		<jsp:include page="/includes/footer.jsp" />


	</div>
</body>
</html>