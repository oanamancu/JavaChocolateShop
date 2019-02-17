<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="chocolate shop java spring">
<meta name="author" content=" Mancu Oana">

<title>ChocolateShop - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap readable Theme -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/shop-homepage.css" rel="stylesheet">

<link href="${css}/dataTable.min.css" rel="stylesheet">
<!-- Bootstrap DataTables Theme -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Fontawesome -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
	<div class="se-pre-con"></div>
	<div class="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
			role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">ChocolateShop</a>
				</div>
			</div>
		</nav>

		<!-- Page Content -->
		<div class="content">


			<div class="container">
                
                <!-- displayed if the credentials are wrong -->
				<c:if test="${not empty message}">
					<div class="row">
						<div class="col-xs-12 col-md-offset-2 col-md-8">
							<div class="alert alert-danger fade in">${message}</div>
						</div>
					</div>
				</c:if>

                <!-- displayed when user logs out -->
				<c:if test="${not empty logout}">
					<div class="row">
						<div class="col-xs-12 col-md-offset-2 col-md-8">
							<div class="alert alert-success">${logout}</div>
						</div>
					</div>
				</c:if>

				<div class="row" style="padding-bottom: 60px;">

					<div class="col-md-offset-3 col-md-6">

						<div class="panel panel-primary">

							<div class="panel-body">
								<form action="${contextRoot}/login" method="POST"
									class="form-horizontal" id="loginForm">
									<div class="form-group">
										<label for="username" class="col-md-4 control-label">Email:
										</label>
										<div class="col-md-8">
											<input type="text" name="username" id="username"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label for="password" class="col-md-4 control-label">Password:
										</label>
										<div class="col-md-8">
											<input type="password" name="password" id="password"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<div><input type="hidden"
												name="${_csrf.parameterName}" value="${_csrf.token}" /> <input
												type="submit" value="Login" class="btn btn-primary" /> 
												</div>
												 <div style="margin-top: 10px;">New user? - <a
												href="${contextRoot}/register">Register Here</a></div>
										</div>
									</div>
								</form>

							</div>
						</div>

					</div>

				</div>

			</div>


		</div>
		<!-- /.container -->

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.min.js"></script>
		<script src="${js}/bootstrap.bundle.min.js"></script>
		<script src="${js}/jquery.js"></script>

		<!-- jQuery validator -->
		<script src="${js}/jquery.validate.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- Self coded JavaScript -->
		<script src="${js}/myapp.js"></script>

	</div>

</body>

</html>

