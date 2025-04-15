package in.shraddha.service;

import java.util.List;

import in.shraddha.entity.Category;

public interface AddCategory {
	
	public void addCategory(String categoryName, String image, String subcategoryName);
	
	public Category findByID(Integer id);

	public List<Category> getAllCategories();
	
	public void deleteCategory(Integer id);
	
}
