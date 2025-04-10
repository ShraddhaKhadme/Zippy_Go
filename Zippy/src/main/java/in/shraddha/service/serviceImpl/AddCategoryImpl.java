package in.shraddha.service.serviceImpl;

import java.util.List;

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
	@Override
	public List<Category> allCategory() {

		return crepo.findAll();
	}
	@Override
	public boolean checkCategory(String category) {
		// TODO Auto-generated method stub
		return crepo.existsByName(category);
	}

	
}
