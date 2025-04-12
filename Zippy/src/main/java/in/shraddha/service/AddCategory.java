package in.shraddha.service;

import java.util.List;

import in.shraddha.entity.Category;

public interface AddCategory {
	public void addCategory(String categoryName, String image, String subcategoryName);

	public List<Category> getAllCategories();
	
}
