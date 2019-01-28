package net.mo.ChocolateShopBackEnd.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.mo.ChocolateShopBackEnd.dao.CategoryDAO;
import net.mo.ChocolateShopBackEnd.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.mo.ChocolateShopBackEnd");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	/*
	  @Test 
	  
	  public void testAddCategory() { 
	  
	      category = new Category();	  
	      category.setName("Tablets");	  
	      assertEquals("Something went wrong while adding a category inside the table!", true,
	             categoryDAO.add(category)); 
	  }
	/
	
	
   /*
	@Test
	public void testGetCategory() {

		category = categoryDAO.get(3);
		assertEquals("Something went wrong while fetching a single category from the table!", "Chocolate Boxes",
				 category.getName()); 
	}
	*/
	
	/*
	@Test
	public void testUpdateCategory() {

		category = categoryDAO.get(3);		
		category.setName("Chocolate Boxes");
		assertEquals("Something went wrong while updating a single category in the table!", true,
				 categoryDAO.update(category)); 
	}
	*/
	
/*	@Test
	public void testDeleteCategory() {

		category = categoryDAO.get(6);		
		assertEquals("Something went wrong while updating (deleting) a single category in the table!", true,
				 categoryDAO.delete(category)); 
	} */
	
/*
	@Test
	public void testListCategory() {

		assertEquals("Something went wrong while fetching the list of categories from the table!", 5,
				 categoryDAO.list().size()); 
	}
*/
	
	
	@Test
	public void testCRUDCategory() {
		
		// add operation
		 category = new Category();
		 category.setName("Tablets");	  
		 assertEquals("Something went wrong while adding a category inside the table!", true,
		 categoryDAO.add(category));
		 
		 category = new Category();
		 category.setName("Boxes");	  
		 assertEquals("Something went wrong while adding a category inside the table!", true,
		 categoryDAO.add(category));
		 
		 // fetching & renaming the category
		 category = categoryDAO.get(2);		
			category.setName("Chocolate Boxes");
			assertEquals("Something went wrong while updating a single category in the table!", true,
					 categoryDAO.update(category)); 
		 
		 // delete the category
	     assertEquals("Something went wrong while updating (deleting) a single category in the table!", true,
					 categoryDAO.delete(category)); 
	     
	     //fetching the list
	     assertEquals("Something went wrong while fetching the list of categories from the table!", 1,
				 categoryDAO.list().size());
	}
}
