package net.mo.ChocolateShopFrontEnd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.mo.ChocolateShopBackEnd.dao.CategoryDAO;
import net.mo.ChocolateShopBackEnd.dao.ProductDAO;
import net.mo.ChocolateShopBackEnd.dto.Category;
import net.mo.ChocolateShopBackEnd.dto.Product;
import net.mo.ChocolateShopFrontEnd.exception.ProductNotFoundException;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");

		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClicksHome", true);
		return mv;
	}

	/*
	 * Methods to load all the products and based on category
	 */
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClicksAllProducts", true);
		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		// using categoryDAO to fetch a single category
		Category category = null;
		category = categoryDAO.get(id);

		mv.addObject("title", category.getName());

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		// passing the single category object
		mv.addObject("category", category);

		mv.addObject("userClicksCategoryProducts", true);
		return mv;
	}

	// single product view
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");

		Product product = productDAO.get(id);
		
		if(product == null) 
			throw new ProductNotFoundException();
		
		product.setViews(product.getViews() + 1);
		productDAO.update(product);

		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClicksShowProduct", true);

		return mv;
	}
	
	// login
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name="error",required=false)String error,
			@RequestParam(name="logout",required=false)String logout
			) {
		ModelAndView mv = new ModelAndView("login");
		
		if(error!=null) {
			mv.addObject("message","Invalid username or password!");
		}
		
		if(logout!=null) {
			mv.addObject("logout","Successfully logged out!");
		}
		
		mv.addObject("title","Login");
		return mv;
	}
	
	/* access denied page */
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title","403 - Access Denied");
		mv.addObject("errorTitle","403 - Access Denied");
		mv.addObject("errorDescription","You are not authorized to view this page!");
		return mv;
	}
	
	
	/* Logout */
	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if(auth!=null) {
	    	new SecurityContextLogoutHandler().logout(request,response,auth);
	    }
	    
		return "redirect:/login?logout";
	}
}
