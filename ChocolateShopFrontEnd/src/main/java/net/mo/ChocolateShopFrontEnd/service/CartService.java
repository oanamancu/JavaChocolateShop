package net.mo.ChocolateShopFrontEnd.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mo.ChocolateShopBackEnd.dao.CartLineDAO;
import net.mo.ChocolateShopBackEnd.dao.ProductDAO;
import net.mo.ChocolateShopBackEnd.dto.Cart;
import net.mo.ChocolateShopBackEnd.dto.CartLine;
import net.mo.ChocolateShopBackEnd.dto.Product;
import net.mo.ChocolateShopFrontEnd.model.UserModel;

@Service("cartService")
public class CartService {
     
	  @Autowired 
	  private CartLineDAO cartLineDAO;
	  @Autowired 
	  private ProductDAO productDAO;
	  @Autowired
	  private HttpSession session;
	  
	  //returns the cart of the user that is logged in
	  private Cart getCart() {
		  return ((UserModel)session.getAttribute("userModel")).getCart();
	  }
	  
	  //return all the cart lines
	  public List<CartLine> getCartLines(){
		  return cartLineDAO.list(this.getCart().getId());
	  }

	public String manageCartLine(int cartLineId, int count) {
		
		//fetching the cart line
		CartLine cartline = cartLineDAO.get(cartLineId);
		
		if(cartline == null) {
			return "result=error";
		}
		else {
			
			//updating the cart line
			Product product = cartline.getProduct();
			double oldTotal = cartline.getTotal();
			cartline.setProductCount(count);
			cartline.setBuyingPrice(product.getUnitPrice());
			cartline.setTotal(product.getUnitPrice() * count);
			cartLineDAO.update(cartline);
			
			//updating the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartline.getTotal());
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}
		
	}

	public String deleteCartLine(int cartLineId) {
        
		//fetch the cart line
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null) {
			return "result=error";
		}
		else {
			//updating the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			System.out.println("**************"+cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);
			
			//removing the cart line
			cartLineDAO.remove(cartLine);
			
			return "result=deleted";
		}
	}

	public String addCartLine(int productId) {
		
		String response = null;
		
		Cart cart = this.getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		if(cartLine == null) {
			//add a new cartLine
			cartLine = new CartLine();
			//fetch the product
			Product product = productDAO.get(productId);
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}
		else {
			//if the cartLine has reached the maximum count
			if(cartLine.getProductCount() < 10000) {
				//update the product for the cartLine
				response = this.manageCartLine(cartLine.getId(),cartLine.getProductCount()+1);
			}
			else {
				response = "result=maximum";
			}
		}
		
		return response;
	}
}
