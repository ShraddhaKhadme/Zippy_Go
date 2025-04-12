package in.shraddha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.shraddha.entity.Category;
import in.shraddha.entity.CategoryForm;
import in.shraddha.service.AddCategory;

@Controller
@RequestMapping("/Category")
public class CategoryController {
	
	@Autowired
    private AddCategory categoryService;

    @GetMapping("/add-category")
    public String showCategoryForm(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        return "addcategory";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute CategoryForm categoryForm) {
        categoryService.addCategoryWithSubcategory(
            categoryForm.getCategoryName(),
            categoryForm.getImage(),
            categoryForm.getSubcategoryName()
        );
        return "redirect:/add-category?success";
    }

    @GetMapping("/add-product")
    public String showProductForm(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "productform";
    }
	
	

}
