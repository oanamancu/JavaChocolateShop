
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<c:if test="${not empty message}">
		<div class="col-12">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				${message}
			</div>
		</div>
	</c:if>

	<div class="row">

		<div class="col-12 col-md-offset-2 col-md-8"
			style="margin-right: auto; margin-left: auto;">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<h4 style="color: orangered;">Product Management</h4>
				</div>

				<div class="panel-body">

					<!-- Form elements -->
					<sf:form modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">
						<fieldset style="color: #34000D;">
							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="name"> <b>Product
										Name:</b>
								</label>
								<div class="col-md-6">
									<sf:input type="text" path="name" id="name"
										placeholder="Product name" class="form-controler form-control" />
									<sf:errors path="name" cssClass="help-block" element="em" />
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="categoryId">
									<b>Category:</b>
								</label>
								<div class="col-md-5" style="padding-left: 4px;">
									<sf:select class="form-controler form-control"
										style="width: 348px;" id="categoryId" path="categoryId"
										items="${categories}" itemLabel="name" itemValue="id" />
									<div class="text-right" style="width: 348px;">
										<button type="button" data-toggle="modal"
											data-target="#myCategoryModal" class="btn btn-primary btn-xs">
											Add Category</button>
									</div>
								</div>
							</div>

							<!-- File element for img upload -->
							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="file"> <b>Select
										an image:</b>
								</label>
								<div class="col-md-6">
									<sf:input type="file" path="file" id="file"
										class="form-controler form-control" />
									<sf:errors path="file" cssClass="help-block" element="em" />
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="description">
									<b>Description:</b>
								</label>
								<div class="col-md-6">
									<sf:textarea rows="5" cols="50" path="description"
										id="description" class="form-control"
										placeholder="Description"></sf:textarea>
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="unitPrice"><b>Unit
										Price:</b></label>
								<div class="col-md-6">
									<sf:input type="number" min="0.01" step="0.01" path="unitPrice"
										id="unitPrice" class="form-control"
										placeholder="Unit price in $" />
									<sf:errors path="unitPrice" cssClass="help-block" element="em" />
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="weight"><b>Weight:</b></label>
								<div class="col-md-6">
									<sf:input type="number" min="0.0" step="0.01" path="weight"
										id="weight" class="form-control" placeholder="Weight in grams" />
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="dimensions">
									<b>Dimensions:</b>
								</label>
								<div class="col-md-6">
									<sf:input type="text" path="dimensions" id="dimensions"
										placeholder="L x W x H unit"
										class="form-controler form-control" />
								</div>
							</div>

							<div class="form-group row">
								<label class="col-sm-3 col-form-label" for="ingredients">
									<b>Ingredients:</b>
								</label>
								<div class="col-md-6">
									<sf:textarea rows="5" cols="50" path="ingredients"
										id="ingredients" class="form-control"
										placeholder="Ingredients"></sf:textarea>
								</div>
							</div>

							<div class="form-group">
								<div class="">
									<input style="width: 100px;" type="submit" name="submit"
										id="submit" value="Save" class="btn btn-primary" />

									<!-- Hidden fields -->
									<sf:hidden path="id" />
									<sf:hidden path="code" />
									<sf:hidden path="active" />
									<sf:hidden path="purchases" />
									<sf:hidden path="views" />
								</div>
							</div>

						</fieldset>
					</sf:form>

				</div>

			</div>

		</div>

	</div>


	<div class="row" style="margin-top: 40px; margin-left: 10px;">
		<div class="col-xs-12" style="width: 100%;">
			<h3>Available Products</h3>
		</div>
		<div class="col-xs-12 row" style="overflow: auto; width: 100%;">

			<!-- Products table for Admin -->
			<table id="adminProductsTable"
				class="table stable-stripped table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Price</th>
						<th>Active</th>
						<th>Edit</th>
					</tr>
				<thead>
				<tfoot>
					<tr>
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Price</th>
						<th>Active</th>
						<th>Edit</th>
					</tr>
				</tfoot>
			</table>

		</div>

	</div>


	<div class="modal fade" id="myCategoryModal" role="dialog"
		tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<!--  Modal Header  -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title">Add new category</h4>
				</div>
				<div class="modal-body">
					<!-- Category spring form -->
					<sf:form id="categoryForm" modelAttribute="category"
						action="${contextRoot}/manage/category" method="POST"
						class="form-horizontal">
						<div class="form-group">
							<label for="category_name" class="control-label col-md-4">Category
								name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="category_name" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add Category" class="btn btn-primary"/>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>

</div>