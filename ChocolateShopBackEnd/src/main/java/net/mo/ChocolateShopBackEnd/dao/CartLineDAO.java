package net.mo.ChocolateShopBackEnd.dao;

import java.util.List;

import net.mo.ChocolateShopBackEnd.dto.Cart;
import net.mo.ChocolateShopBackEnd.dto.CartLine;
import net.mo.ChocolateShopBackEnd.dto.OrderDetail;

public interface CartLineDAO {

	public List<CartLine> list(int cartId);
	public CartLine get(int id);	
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	
	// fetch the CartLine based on cartId and productId
	public CartLine getByCartAndProduct(int cartId, int productId);		
		
	// updating the cart
	boolean updateCart(Cart cart);
	
	// adding order details
	boolean addOrderDetail(OrderDetail orderDetail);

	
}
