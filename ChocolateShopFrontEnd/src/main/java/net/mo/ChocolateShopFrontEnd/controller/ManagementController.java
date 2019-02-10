package net.mo.ChocolateShopFrontEnd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.mo.ChocolateShopBackEnd.dao.CategoryDAO;
import net.mo.ChocolateShopBackEnd.dao.ProductDAO;
import net.mo.ChocolateShopBackEnd.dto.Category;
import net.mo.ChocolateShopBackEnd.dto.Product;
import net.mo.ChocolateShopFrontEnd.util.FileUploadUtility;
import net.mo.ChocolateShopFrontEnd.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClicksManageProducts", true);
		mv.addObject("title", "Products Management");

		Product nProduct = new Product();
		// set few of the fields
		nProduct.setActive(true);
		mv.addObject("product", nProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product submitted successfully!");
			}
			else if(operation.equals("category")) {
			    mv.addObject("message", "Category submitted successfully!");
			}
		}

		return mv;
	}

	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClicksManageProducts", true);
		mv.addObject("title", "Products Management");

		// fetching product from DB
		Product nProduct = productDAO.get(id);
		mv.addObject("product", nProduct);

		return mv;
	}

	// handling product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
			Model model, HttpServletRequest request) {

		// handle image validation for new products
		if (mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, results);
		}
		else {
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct, results);
			}
		}

		// check if there are any errors
		if (results.hasErrors()) {

			model.addAttribute("userClicksManageProducts", true);
			model.addAttribute("title", "Products Management");

			return "page";
		}

		logger.info(mProduct.toString());

		// set picture name
		MultipartFile file = mProduct.getFile();
		mProduct.setImage(mProduct.getCode() + "." + FilenameUtils.getExtension(file.getOriginalFilename()));

		if (mProduct.getId() == 0) {
			// create a new product record - new
			productDAO.add(mProduct);
		} else {
			// update the product - edit
			productDAO.update(mProduct);
		}

		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/products?operation=product";
	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {

		// fetching the product from DB
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();

		// updating activation status
		product.setActive(!isActive);

		// updating product in DB
		productDAO.update(product);

		return (isActive) ? "You have successfully deactivated the product with the id " + product.getId()
				: "You have successfully activated the product with the id " + product.getId();
	}
	
	
	// to handle category submission
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}
	

	// returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
	
	@ModelAttribute("category")
	public Category getCategory() {		
		return new Category();
	}

}
