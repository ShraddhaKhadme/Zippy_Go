package in.shraddha.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shraddha.entity.Category;
import in.shraddha.repository.CategoryRepo;
import in.shraddha.service.AddCategory;

@Service
public class AddCategoryImpl implements AddCategory{

	@Autowired
	private CategoryRepo crepo;
	@Override
	public Integer saveCategory(Category category) {
		// TODO Auto-generated method stub
		return crepo.save(category).getId();
	}

}
