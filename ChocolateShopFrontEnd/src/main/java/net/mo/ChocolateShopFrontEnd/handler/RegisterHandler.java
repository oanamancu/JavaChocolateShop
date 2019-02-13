package net.mo.ChocolateShopFrontEnd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import net.mo.ChocolateShopBackEnd.dao.UserDAO;
import net.mo.ChocolateShopBackEnd.dto.Address;
import net.mo.ChocolateShopBackEnd.dto.Cart;
import net.mo.ChocolateShopBackEnd.dto.User;
import net.mo.ChocolateShopFrontEnd.model.RegisterModel;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
      
	 public RegisterModel init() {	 
		 return new RegisterModel();
	 }
	  
	 public void addUser(RegisterModel registerModel, User user) {	 
		 registerModel.setUser(user);
	 }
	 
	 public void addBilling(RegisterModel registerModel, Address address) {	 
		 registerModel.setAddress(address);
	 }
	 
	 
	 public String validateUser(User user, MessageContext error) {
		 String transitionValue = "success";
		 
		 //checking if password matches confirm password
		 if(!user.getPassword().equals(user.getConfirmPassword())) {
			 
			 error.addMessage(new MessageBuilder()
					 .error()
					 .source("confirmPassword")
					 .defaultText("Passwords do not match!")
					 .build());
			 transitionValue = "failure";
		 }
		 
		 //checking the uniqueness of the email id
		 if(userDAO.getByEmail(user.getEmail()) != null) {
			 
			 error.addMessage(new MessageBuilder()
					 .error()
					 .source("email")
					 .defaultText("This email address is already used!")
					 .build());	 
			 transitionValue = "failure";
		 }
		 
		 return transitionValue;
	 }
	 
	 public String saveAll(RegisterModel model) {
		 String transitionValue = "success";
		 
		 //fetch the user
		 User user = model.getUser();
		 if(user.getRole().equals("USER")) {
			 Cart cart = new Cart();
			 cart.setUser(user);
			 user.setCart(cart);
		 }
		 //save the user
		 userDAO.add(user);
		 
		 //get the Address
		 Address billing = model.getAddress();
		 billing.setUser(user);
		 billing.setBilling(true);
		 
		 //save the address
		 userDAO.addAddress(billing);
		 
		 return transitionValue;
	 }
}
