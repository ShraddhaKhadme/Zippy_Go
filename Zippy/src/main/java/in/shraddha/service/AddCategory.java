package in.shraddha.service;

import java.util.List;

import in.shraddha.entity.Category;

public interface AddCategory {
	public Integer saveCategory(Category category);

	public List<Category> allCategory();
}
