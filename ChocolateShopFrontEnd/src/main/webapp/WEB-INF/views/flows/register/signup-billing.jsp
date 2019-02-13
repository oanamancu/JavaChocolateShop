<%@include file="../shared/flows-header.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>			
	<div class="container">
		
			<div class="row">
			
			<div class="col-md-6 col-md-offset-3" style="margin-right: auto; margin-left: auto;">
				
				<div class="panel panel-primary">
				
					<div class="panel-heading">
						<h4 style="color: orangered;">Sign Up - Address</h4>
					</div>
					
					<div class="panel-body">
										
						<sf:form
							method="POST"
							modelAttribute="billing"
							class="form-horizontal"
							id="billingForm"
						>
						
							
							<div class="form-group row">
								<label class="control-label col-sm-3" for="address">Address </label>
								<div class="col-md-6">
									<sf:input type="text" path="address" class="form-control"
										placeholder="Enter address" />
									<sf:errors path="address" cssClass="help-block" element="em"/> 
								</div>
							</div>

							<div class="form-group row">
								<label class="control-label col-sm-3" for="city">City</label>
								<div class="col-md-6">
									<sf:input type="text" path="city" class="form-control"
										placeholder="Enter City Name" />
									<sf:errors path="city" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							<div class="form-group row">
								<label class="control-label col-sm-3" for="postalCode">Postal Code</label>
								<div class="col-md-6">
									<sf:input type="text" path="postalCode" class="form-control"
										placeholder="XXXXXX" />
									<sf:errors path="postalCode" cssClass="help-block" element="em"/> 
								</div>
							</div>							

							<div class="form-group row">
								<label class="control-label col-sm-3" for="country">Country</label>
								<div class="col-md-6">
									<sf:input type="text" path="country" class="form-control"
										placeholder="Enter Country Name" />
									<sf:errors path="country" cssClass="help-block" element="em"/> 
								</div>
							</div>
							
							
							<div class="form-group">
								<div>
									<button type="submit" name="_eventId_personal" class="btn btn-primary">
										<i class="fas fa-chevron-left"></i> Back
									</button>								
									<button type="submit" name="_eventId_confirm" class="btn btn-primary">
										Next <i class="fas fa-chevron-right"></i>
									</button>																	 
								</div>
							</div>
						
						
						</sf:form>					
					
					
					</div>
				
				
				</div>
			
			
			</div>
		
		
		</div>
		
		  
	</div>

<%@include file="../shared/flows-footer.jsp" %>		
		
