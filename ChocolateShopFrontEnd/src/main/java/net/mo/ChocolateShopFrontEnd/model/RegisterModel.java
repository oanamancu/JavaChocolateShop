package net.mo.ChocolateShopFrontEnd.model;

import java.io.Serializable;

import net.mo.ChocolateShopBackEnd.dto.Address;
import net.mo.ChocolateShopBackEnd.dto.User;

public class RegisterModel implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private User user;
	private Address address;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
