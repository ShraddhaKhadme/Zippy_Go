package in.shraddha.service;

import java.util.List;

import in.shraddha.entity.Category;

public interface AddCategory {
	public void addCategoryWithSubcategory(String categoryName, String image, String subcategoryName);

	public List<Category> getAllCategories();
	
}
