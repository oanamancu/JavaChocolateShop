<script>
	window.categoryId = '${category.id}';
</script>
<div class="container" style="margin-bottom: 20px;">
	<!-- Breadcrumb -->
	<div class="row">
		<div class="col-xs-12" style="width: 100%;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${contextRoot}/show/all/products">Products</a></li>
				<li class="breadcrumb-item active">${product.name}</li>
			</ol>
		</div>
	</div>
	<div class="row">
		<!-- Product image -->
		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
				<img src="${images}/${product.image}" class="img img-responsive" />
			</div>
		</div>
		<!-- Product description  -->
		<div class="col-xs-12 col-sm-8">
			<h3>${product.name}</h3>
			<hr />
			<c:if test="${product.description != null}">
				<p>${product.description}</p>
				<hr />
			</c:if>
			<h4>$${product.unitPrice}</h4>
			<hr />
			<c:if test="${product.weight != 0}">
				<h6>
					<strong>Weight:</strong> ${product.weight}
				</h6>
				<hr />
			</c:if>
			<c:if test="${product.dimensions != null}">
				<h6>
					<strong>Dimensions:</strong> ${product.dimensions}
				</h6>
				<hr />
			</c:if>
			<c:if test="${product.ingredients != null}">
				<h6>
					<strong>Ingredients:</strong> ${product.ingredients}
				</h6>
			</c:if>
			<br /> 
			<security:authorize access="hasAuthority('USER')"> 
			<a href="${contextRoot}/cart/add/${product.id}/product"
				class="btn btn-success"> <i class="fas fa-cart-arrow-down"></i>Add
				to Cart
			</a> 
			</security:authorize>
			<security:authorize access="hasAuthority('ADMIN')">
			<a href="${contextRoot}/manage/${product.id}/product"
				class="btn btn-success"><i class="fas fa-marker" style="margin-right: 5px;"></i> Edit
			</a> 
			</security:authorize>
			<a href="${contextRoot}/show/all/products" class="btn btn-primary">Back</a>
		</div>
	</div>
</div>