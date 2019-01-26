package net.mo.ChocolateShopBackEnd.dao;

import java.util.List;

import net.mo.ChocolateShopBackEnd.dto.Category;

public interface CategoryDAO {
	
      List<Category> list();
      Category get(int id);
}
