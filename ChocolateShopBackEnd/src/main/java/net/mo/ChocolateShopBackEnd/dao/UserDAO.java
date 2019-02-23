package net.mo.ChocolateShopBackEnd.dao;

import java.util.List;

import net.mo.ChocolateShopBackEnd.dto.Address;
import net.mo.ChocolateShopBackEnd.dto.Cart;
import net.mo.ChocolateShopBackEnd.dto.User;

public interface UserDAO {

	// user related operation
	User getByEmail(String email);
	User get(int id);
    boolean add(User user);
	
	// adding and updating a new address
	Address getAddress(int addressId);
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	
	//alternative => better performance (less queries in the background)
	//Address getBillingAddress(int userId);
	//List<Address> listShippingAddresses(int userId);
	
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);
	

	
}
