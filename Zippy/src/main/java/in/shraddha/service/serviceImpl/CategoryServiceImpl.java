package in.shraddha.service.serviceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shraddha.entity.Category;
import in.shraddha.entity.SubCategory;
import in.shraddha.repository.CategoryRepo;
import in.shraddha.repository.SubCategoryRepo;
import in.shraddha.service.AddCategory;

@Service
public class CategoryServiceImpl implements AddCategory {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private SubCategoryRepo subcategoryRepo;

    public void addCategory(String categoryName, String image, String subcategoryName) {
        System.out.println("Inside addCategoryWithSubcategory");
        System.out.println("categoryName = " + categoryName);
        System.out.println("subcategoryName = " + subcategoryName);

        Category category = categoryRepo.findByName(categoryName)
                .orElseGet(() -> {
                    Category newCat = new Category();
                    newCat.setName(categoryName);
                    newCat.setImg(image);
                    return categoryRepo.save(newCat);
                });

        SubCategory subcategory = new SubCategory();
        subcategory.setName(subcategoryName);
        subcategory.setCategory(category);
        subcategoryRepo.save(subcategory);
    }


    
    public List<Category> getAllCategories() {
        return categoryRepo.findAllWithSubcategories();
    }



	@Override
	public Category findByID(Integer id) {
		// TODO Auto-generated method stub
		return categoryRepo.findById(id).orElse(null);
	}



	@Override
	public void deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		categoryRepo.deleteById(id);
	}

}
