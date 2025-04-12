package in.shraddha.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shraddha.entity.SubCategory;
import in.shraddha.repository.SubCategoryRepo;
import in.shraddha.service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

    @Autowired
    private SubCategoryRepo subCategoryRepo;

    public List<SubCategory> getSubcategoriesByCategoryId(Integer categoryId) {
        return subCategoryRepo.findByCategoryId(categoryId);
    }
}
