package in.shraddha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.shraddha.entity.Category;
import in.shraddha.entity.Product;
import in.shraddha.service.AddCategory;
import in.shraddha.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService pservice;

    @Autowired
    private AddCategory cservice;

    @GetMapping("/")
    public String loadPage(Model model) {
        List<Category> list = cservice.getAllCategories();
        model.addAttribute("product", new Product()); 
        model.addAttribute("list", list);              
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
        model.addAttribute("list", cservice.getAllCategories());
        model.addAttribute("message", message);

        return "productform";
    }
}
