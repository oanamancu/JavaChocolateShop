package net.mo.ChocolateShopBackEnd.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mo.ChocolateShopBackEnd.dao.CategoryDAO;
import net.mo.ChocolateShopBackEnd.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();
	
	static {
		Category category; 
		
		category = new Category();
		category.setId(1);
		category.setName("Tablets");
		categories.add(category);
		
		category = new Category();
		category.setId(2);
		category.setName("Pralines");
		categories.add(category);
		
		category = new Category();
		category.setId(3);
		category.setName("Gift Boxes");
		categories.add(category);
		
		category = new Category();
		category.setId(4);
		category.setName("Christmas");
		categories.add(category);
	}
	
	@Override
	public List<Category> list() {
		return categories;
	}

	@Override
	public Category get(int id) {
		for(Category category: categories) {
			if(category.getId() == id)
				return category;
		}
		return null;
	}

}
