<%@include file="../shared/flows-header.jsp" %>
<div class="container">
	
	<div class="row">
	
		<div class="col-sm-6">
	
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<h4 style="color:orangered; text-align: center;">Personal Details</h4>
				</div>
			
				<div class="panel-body">
					<div class="text-center">
						<h4><strong>${registerModel.user.firstName} ${registerModel.user.lastName} </strong></h4>
						<h5><strong>Email :</strong> ${registerModel.user.email}</h5>
						<h5><strong>Contact :</strong>${registerModel.user.contactNumber}</h5>
						<h5><strong>Role :</strong> ${registerModel.user.role}</h5>
						<p>
							<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">Edit</a>
						</p>
					</div>
				</div>
			
			</div>
					
		
		</div>
		
		<div class="col-sm-6">
		
			<div class="panel panel-primary">
				
				<div class="panel-heading">
					<h4 style="color:orangered; text-align: center;">Billing Address</h4>
				</div>
			
				<div class="panel-body">
					<div class="text-center">
						<h5>${registerModel.address.address} </h5>
						<h5>${registerModel.address.postalCode} </h5>
						<h5>${registerModel.address.city} </h5>
						<h5>${registerModel.address.country}</h5>
						<p>
							<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">Edit</a>
						</p>
					</div>
				</div>
			
			</div>
		
		</div>
	
	</div>
	
	<div class="row text-center">
		<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-lg btn-primary" style="margin-right: auto; margin-left: auto; margin-top:100px; margin-bottom:20px;">Confirm</a>		
	</div>

</div>
<%@include file="../shared/flows-footer.jsp" %>		
