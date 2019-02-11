package net.mo.ChocolateShopBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mo.ChocolateShopBackEnd.dao.UserDAO;
import net.mo.ChocolateShopBackEnd.dto.Address;
import net.mo.ChocolateShopBackEnd.dto.Cart;
import net.mo.ChocolateShopBackEnd.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mo.ChocolateShopBackEnd");
		context.refresh();

		userDAO = (UserDAO) context.getBean("userDAO");
	}

	/*
	 * @Test public void testAddUser() {
	 * 
	 * user = new User() ; user.setFirstName("A"); user.setLastName("Aaa");
	 * user.setEmail("a@gmail.com"); user.setContactNumber("1234512345");
	 * user.setRole("CUSTOMER"); user.setEnabled(true); user.setPassword("12345");
	 * 
	 * // add the user assertEquals("Failed to add the user!", true,
	 * userDAO.add(user));
	 * 
	 * address = new Address(); address.setAddress("A block A street");
	 * address.setCity("A City"); address.setCountry("A Country");
	 * address.setPostalCode("021410"); address.setBilling(true);
	 * 
	 * //link the user with the address using user id
	 * address.setUserId(user.getId());
	 * 
	 * // add the address assertEquals("Failed to add the billing address!", true,
	 * userDAO.addAddress(address));
	 * 
	 * if(user.getRole().equals("CUSTOMER")) {
	 * 
	 * //create a cart for this user cart = new Cart(); cart.setUser(user);
	 * assertEquals("Failed to add cart!", true, userDAO.addCart(cart));
	 * 
	 * //add a shipping address for this user address = new Address();
	 * address.setAddress("B Street"); address.setCity("B City");
	 * address.setCountry("B Country"); address.setPostalCode("021206");
	 * address.setShipping(true);
	 * 
	 * //link it with the user address.setUserId(user.getId());
	 * 
	 * assertEquals("Failed to add the shipping address!", true,
	 * userDAO.addAddress(address)); } }
	 */

	/*
	 * @Test public void testAddUser() {
	 * 
	 * user = new User() ; user.setFirstName("A"); user.setLastName("Aaa");
	 * user.setEmail("a@gmail.com"); user.setContactNumber("1234512345");
	 * user.setRole("CUSTOMER"); user.setEnabled(true); user.setPassword("12345");
	 * 
	 * if(user.getRole().equals("CUSTOMER")) {
	 * 
	 * //create a cart for this user cart = new Cart(); cart.setUser(user);
	 * 
	 * //attach cart with the user user.setCart(cart); }
	 * 
	 * // add the user assertEquals("Failed to add the user!", true,
	 * userDAO.add(user)); }
	 */

	/*
	 * @Test public void testUpdateCart() {
	 * 
	 * user = userDAO.getByEmail("a@gmail.com"); cart = user.getCart();
	 * cart.setGrandTotal(5555); cart.setCartLines(2);
	 * assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart)); }
	 */

	/*
	 * @Test public void testAddAddress() {
	 * 
	 * //add user user = new User() ; user.setFirstName("A");
	 * user.setLastName("Aaa"); user.setEmail("a@gmail.com");
	 * user.setContactNumber("1234512345"); user.setRole("CUSTOMER");
	 * user.setEnabled(true); user.setPassword("12345");
	 * assertEquals("Failed to add the user!", true, userDAO.add(user));
	 * 
	 * //add billing address address = new Address();
	 * address.setAddress("A block A street"); address.setCity("A City");
	 * address.setCountry("A Country"); address.setPostalCode("021410");
	 * address.setBilling(true); //attach the user to the address
	 * address.setUser(user); assertEquals("Failed to add address!", true,
	 * userDAO.addAddress(address));
	 * 
	 * //add shipping address address = new Address();
	 * address.setAddress("B Street"); address.setCity("B City");
	 * address.setCountry("B Country"); address.setPostalCode("021206");
	 * address.setShipping(true); address.setUser(user);
	 * assertEquals("Failed to add shipping address!", true,
	 * userDAO.addAddress(address)); }
	 */

	/*
	 * @Test public void testAddress() {
	 * 
	 * user = userDAO.getByEmail("a@gmail.com"); address = new Address();
	 * address.setAddress("C Street"); address.setCity("C City");
	 * address.setCountry("C Country"); address.setPostalCode("021206");
	 * address.setShipping(true); address.setUser(user);
	 * assertEquals("Failed to add shipping address!", true,
	 * userDAO.addAddress(address)); }
	 */

	@Test
	public void testGetAddress() {
		user = userDAO.getByEmail("a@gmail.com");
		assertEquals("Failed to fetch the list of addresses and the size doesn't match!", 2,
				userDAO.listShippingAddresses(user).size());
		assertEquals("Failed to fetch the list of billing addresses and the size doesn't match!", "A City",
				userDAO.getBillingAddress(user).getCity());	}

}
