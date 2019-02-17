
<div class="container">

	<div class="row">

		<!-- Sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>

		<!-- display actual products -->
		<div class="col-md-9">
			<!-- Breadcrumb component -->
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClicksAllProducts == true}">
						<script>
							window.categoryId = '';
						</script>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">All Products</li>
						</ol>
					</c:if>
					<c:if test="${userClicksCategoryProducts == true}">
						<script>
							window.categoryId = '${category.id}';
						</script>
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">Category</li>
							<li class="breadcrumb-item active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="container-fluid">
						<div class="table-responsive">
							<table id="productListTable"
								class="table table-striped table-borderd display"
								style="width: 100%">
								<thead>
									<tr>
										<th></th>
										<th>Name</th>
										<th>Price</th>
										<th></th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th></th>
										<th>Name</th>
										<th>Price</th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>

</div>