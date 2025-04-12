package in.shraddha.service;

import java.util.List;

import in.shraddha.entity.SubCategory;

public interface SubCategoryService {

	 public List<SubCategory> getSubcategoriesByCategoryId(Integer categoryId);
}
