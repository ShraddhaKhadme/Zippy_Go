package in.shraddha.service;

import java.util.List;

import in.shraddha.entity.SubCategory;

public interface SubCategoryService {
	
	public SubCategory findById(Integer id);

	 public List<SubCategory> getSubcategoriesByCategoryId(Integer categoryId);
}
