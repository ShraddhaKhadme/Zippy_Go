package in.shraddha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.shraddha.entity.Category;
import in.shraddha.entity.Product;
import in.shraddha.entity.SubCategory;
import in.shraddha.service.AddCategory;
import in.shraddha.service.ProductService;
import in.shraddha.service.SubCategoryService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService pservice;

    @Autowired
    private AddCategory cservice;

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/form")
    public String loadPage(Model model) {
        List<Category> categories = cservice.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());           
        return "productform";
    }

    @PostMapping("/form")
    public String handleProductForm(@ModelAttribute Product p, Model model) {
        Integer id = pservice.saveProduct(p);

        String message;
        if (id != null && id > 0) {
            message = "Product '" + id + "' added successfully!";
        } else {
            message = "Unable to add product. Please try again...";
        }

        model.addAttribute("product", new Product());
        model.addAttribute("categories", cservice.getAllCategories());
        model.addAttribute("message", message);

        return "productform";
    }

    // This will fetch subcategories dynamically based on the selected category
    @GetMapping("/getSubcategories")
    public String getSubcategories(@RequestParam("categoryId") Integer categoryId, Model model) {
        List<SubCategory> subcategories = subCategoryService.getSubcategoriesByCategoryId(categoryId);
        model.addAttribute("subcategories", subcategories);
        return "fragments :: subcategoryOptions";  // This is for Thymeleaf fragment.html class
    }
}
