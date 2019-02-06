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
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
    <div class="se-pre-con"></div>
	<div class="wrapper">

		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		<div class="content">

			<!-- Loading the home content -->
			<c:if test="${userClicksHome == true}">
				<%@include file="home.jsp"%>
			</c:if>

			<!-- loading products -->
			<c:if
				test="${userClicksAllProducts == true or userClicksCategoryProducts == true }">
				<%@include file="listProducts.jsp"%>
			</c:if>
			
			<!-- Product details -->
			<c:if test="${userClicksShowProduct == true}">
			    <%@include file="singleProduct.jsp" %>
			</c:if>
			
			<!-- Products Management -->
			<c:if test="${userClicksManageProducts == true}">
			    <%@include file="manageProducts.jsp" %>
			</c:if>

		</div>
		<!-- /.container -->

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>
		
		<!-- jQuery -->
		<script src="${js}/jquery.min.js"></script> 
		<script src="${js}/bootstrap.bundle.min.js"></script>
		<script src="${js}/jquery.js"></script>
		
		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>
		
        <!-- DataTable Plug in -->
        <script src="${js}/jquery.dataTables.js"></script> 
        
        <!-- DataTable Bootstrap Script -->
        <script src="${js}/dataTables.bootstrap.js"></script> 
         
		<!-- Self coded JavaScript -->
		<script src="${js}/myapp.js"></script>

	</div>

</body>

</html>

