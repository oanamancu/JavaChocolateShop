
<style>
body {
	padding-top: 0px;
}
</style>

<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<div class="container">
    
    <c:if test="${not empty message}">
          <div class="alert alert-info" style="margin-top:100px">
               <h3 class="text-center">
                   ${message}
               </h3>
          </div>
    </c:if>
    
	<c:choose>
		<c:when test="${not empty cartLines}">
			<table id="cart" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th style="width: 20%">Image</th>
						<th style="width: 30%">Name</th>
						<th style="width: 10%">Price</th>
						<th style="width: 8%">Quantity</th>
						<th style="width: 10%">Subtotal</th>
						<th style="width: 10%"></th>
					</tr>
				</thead>
				<tbody>
				   <c:forEach items="${cartLines}" var="cartLine">
				   <tr>
						<td>
							<div class="col-sm-3 hidden-xs">
								<img src="${images}/${cartLine.product.image}" alt="${cartline.product.name}"
									class="img-responsive cartImg" />
							</div>
						</td>
						<td data-th="Product">
							<div class="row">
								<div class="col-sm-9">
									<h5 class="nomargin">${cartLine.product.name}</h5>
								</div>
							</div>
						</td>
						<td data-th="Price">$${cartLine.buyingPrice}</td>
						<td data-th="Quantity"><input id="count_${cartLine.id}" min="1" max="10000" type="number"
							class="form-control text-center" value="${cartLine.productCount}"></td>
						<td data-th="Subtotal">$${cartLine.total }</td>
						<td class="actions" data-th="">
							<div class="btn-group" role="group">
								<button type="button" name="refreshCart" value="${cartLine.id}" class="btn btn-info btn-sm">
									<i class="fa fa-refresh"></i>
								</button>
								<a href="${contextRoot}/cart/${cartLine.id}/delete"  class="btn btn-primary btn-sm" style="margin-left: 5px;">
									<i class="fa fa-trash-o"></i>
								</a>
							</div>
						</td>
					</tr>
				   </c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td><a href="${contextRoot}/show/all/products" class="btn btn-primary"><i
								class="fa fa-angle-left"></i> Continue Shopping</a></td>
						<td colspan="2" class="hidden-xs"></td>
						<td class="hidden text-center"><strong>Total $${userModel.cart.grandTotal }</strong></td>
						<td><a href="#" class="btn btn-success btn-block">Checkout
								<i class="fa fa-angle-right"></i>
						</a></td>
					</tr>
				</tfoot>
			</table>
		</c:when>
		<c:otherwise>
			<div class="jumbotron">
				<div class="text-center">
					<h1>Your cart is empty!</h1>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>