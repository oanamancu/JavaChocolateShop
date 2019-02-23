package net.mo.ChocolateShopBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mo.ChocolateShopBackEnd.dao.CartLineDAO;
import net.mo.ChocolateShopBackEnd.dao.ProductDAO;
import net.mo.ChocolateShopBackEnd.dao.UserDAO;
import net.mo.ChocolateShopBackEnd.dto.Cart;
import net.mo.ChocolateShopBackEnd.dto.CartLine;
import net.mo.ChocolateShopBackEnd.dto.Product;
import net.mo.ChocolateShopBackEnd.dto.User;

public class CartLineTestCase {

	

	private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	
	private CartLine cartLine = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mo.ChocolateShopBackEnd");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	
	@Test
	public void testAddCartLine() {
		
		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("test1@gmail.com");		
		Cart cart = user.getCart();
		
		// fetch the product 
		Product product = productDAO.get(1);
		
		// Create a new CartLine
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the cartLine", true, cartLineDAO.add(cartLine));
		
		//updating the card
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		
		assertEquals("Failed to update the cart", true, cartLineDAO.updateCart(cart));
		
	}
	
		
}
