$(function() {

	// for adding a loader
	$(window).load(function() {
		setTimeout(function() {
			$(".se-pre-con").fadeOut("slow");
		}, 500);
	});

	// for handling CSRF token
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');

	if ((token != undefined && header != undefined)
			&& (token.length > 0 && header.length > 0)) {
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	}

	// active menu
	switch (menu) {

	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Products Management':
		$('#manageProducts').addClass('active');
		break;
	case 'Shopping Cart':
		$('#userModel').addClass('active');
		break;
	default:
		$('#home').addClass("active");
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + categoryId).addClass('active');
		break;
	}

	// code for jQuery dataTable
	var $table = $('#productListTable');
	if ($table.length) {

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({
					aaSorting : [],
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'image',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '" class="dataTableImg" />';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '$' + data;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><i class="fas fa-eye"></i></a> &#160;';
									if (userRole == 'ADMIN') {
										str += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/product" class="btn btn-success"><i class="fas fa-marker"></i></a>';
									} else {
										str += '<a href="'
												+ window.contextRoot
												+ '/cart/add/'
												+ data
												+ '/product" class="btn btn-success"><i class="fas fa-cart-arrow-down"></i></a>';
									}

									return str;
								}
							} ]
				});
	}

	// --------------------------------------
	// data table for admin
	// ------------------------------------

	var $adminProductsTable = $('#adminProductsTable');
	if ($adminProductsTable.length) {

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminProductsTable
				.DataTable({
					aaSorting : [],
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [

							{
								data : 'id'
							},

							{
								data : 'image',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '" class="adminDataTableImg" />';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '$' + data;
								}
							},
							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<label class="switch">';
									if (data) {
										str += '<input type="checkbox" checked="checked" value="'
												+ row.id + '" />';
									} else {
										str += '<input type="checkbox" value="'
												+ row.id + '" />';
									}
									str += '<div class="slider round"></div>';
									str += '</label>';
									return str;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="' + window.contextRoot
											+ '/manage/' + data + '/product"';
									str += 'class="btn btn-primary"> <i class="fas fa-edit"></i>';
									str += '</a>';
									return str;
								}
							} ],

					initComplete : function() {
						var api = this.api();
						api
								.$('.switch input[type="checkbox"]')
								.on(
										'change',
										function() {

											var checkbox = $(this);
											var checked = checkbox
													.prop('checked');
											var dMsg = (checked) ? 'Do you want to activate the product?'
													: 'Do you want to deacitivate the product?';
											var value = checkbox.prop('value');

											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation and Deactivation',
														message : dMsg,
														callback : function(
																confirmed) {
															if (confirmed) {

																console
																		.log(value);

																var activationUrl = window.contextRoot
																		+ '/manage/product/'
																		+ value
																		+ '/activation';

																$
																		.post(
																				activationUrl,
																				function(
																						data) {
																					bootbox
																							.alert({
																								size : 'medium',
																								title : 'Information',
																								message : data
																							});
																				})
															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}
													});
										});
					}
				});
	}

	// -----------------------------------
	// Validation code for category
	// -----------------------------------

	var $categoryForm = $('#categoryForm');

	if ($categoryForm.length) {
		$categoryForm.validate({

			rules : {
				name : {
					required : true,
					minlength : 2
				}
			},
			messages : {
				name : {
					required : 'Please add the category name!'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				error.addClass('help-block');
				// add the error element after the input element
				error.insertAfter(element);
			}
		});
	}

	// -----------------------------------
	// Validation code for the log in form
	// -----------------------------------

	var $loginForm = $('#loginForm');

	if ($loginForm.length) {
		$loginForm.validate({

			rules : {
				username : {
					required : true,
					email : true
				},
				password : {
					required : true
				}
			},
			messages : {
				username : {
					requiered : 'Please enter the username!',
					email : 'Please enter valid email address!'
				},
				password : {
					required : 'Please enter the password!'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				error.addClass('help-block');
				// add the error element after the input element
				error.insertAfter(element);
			}
		});
	}

	$('button[name="refreshCart"]')
			.click(
					function() {
						var cartLineId = $(this).attr('value');
						var countField = $('#count_' + cartLineId);
						var originalCount = countField.attr('value');
						// do the checking only the count has changed
						if (countField.val() !== originalCount) {
							// check if the quantity is within the specified
							// range
							if (countField.val() < 1 || countField.val() > 3) {
								// set the field back to the original field
								countField.val(originalCount);
								bootbox
										.alert({
											size : 'medium',
											title : 'Error',
											message : 'Product Count should be minimum 1 and maximum 3!'
										});
							} else {
								// use the window.location.href property to send
								// the request to the server
								var updateUrl = window.contextRoot + '/cart/'
										+ cartLineId + '/update?count='
										+ countField.val();
								window.location.href = updateUrl;
							}
						}
					});
});