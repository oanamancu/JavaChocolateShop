package net.mo.ChocolateShopBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mo.ChocolateShopBackEnd.dao.ProductDAO;
import net.mo.ChocolateShopBackEnd.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	
	
	private static ProductDAO productDAO;
	
	
	private Product product;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mo.ChocolateShopBackEnd");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	
	/* @Test
	public void testCRUDProduct() {
		
		product = new Product();
				
		product.setName("Chocolate Box 1");
		product.setImage("pralines13.jpg");
		product.setDescription("The perfect way to replace flowers and protect nature.");
		product.setUnitPrice(21);
		product.setDimensions("25 x 17 x 3.5 cm");
		product.setActive(true);
		product.setCategoryId(3);
		
		assertEquals("Something went wrong while inserting a new product!",
				true,productDAO.add(product));		
		
		
		// reading and updating the category
		product = productDAO.get(6);
		product.setName("Chocolate Box 2");
		assertEquals("Something went wrong while updating the existing record!",
				true,productDAO.update(product));		
				
		assertEquals("Something went wrong while deleting the existing record!",
				true,productDAO.delete(product));		
		
		// list
		assertEquals("Something went wrong while fetching the list of products!",
				6,productDAO.list().size());		
						
	}
	*/
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!",
				5,productDAO.listActiveProducts().size());				
	} 
	
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the list of products!",
				4,productDAO.listActiveProductsByCategory(1).size());
		assertEquals("Something went wrong while fetching the list of products!",
				1,productDAO.listActiveProductsByCategory(3).size());
	} 
		
		

}

