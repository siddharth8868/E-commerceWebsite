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

		<% request.setAttribute("breadcrumb","<a href=\"index.jsp\">Home</a> > <a href=\"about.jsp\">About</a>"); %>

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
				<p>The best selling phones and tablets in the web. We do free
					shipping on all our products.</p>
				<p>Please keep checking our web site for discounts.</p>
			</div>
		</div>

		<!--footer -->
		<jsp:include page="/includes/footer.jsp" />


	</div>
</body>
</html>