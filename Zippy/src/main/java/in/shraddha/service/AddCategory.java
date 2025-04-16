package in.shraddha.service;

import java.util.List;

import in.shraddha.entity.Category;
import in.shraddha.entity.CategoryForm;

public interface AddCategory {
	
	public void addCategory(CategoryForm cat);
	
	public Category findByID(Integer id);

	public List<Category> getAllCategories();
	
	public void deleteCategory(Integer id);
	
	
	
}
