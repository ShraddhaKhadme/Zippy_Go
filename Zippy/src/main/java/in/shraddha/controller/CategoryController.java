package in.shraddha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import in.shraddha.entity.Category;
import in.shraddha.service.AddCategory;

@Controller
@RequestMapping("/Category")
public class CategoryController {
	
	@Autowired
	private AddCategory cservice;
	
	
	@GetMapping("/addCategory")
	public String addCategory() {
		return "addcategory";
	}
	
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute Category category,Model model) {
		Integer cid=cservice.saveCategory(category);
		System.out.println(category.getName());
		if(cid>0) {
			model.addAttribute("msg","Category added successfully");
			System.out.println("Category added successfully : " + category.getName());
			
		}
		else {
			model.addAttribute("msg","Category not added");
		}
		
		return "addcategory";
		
		
	}
	
	@GetMapping("/allCategory")
	public String getAll(Model model)
	{
		List<Category> clist=cservice.allCategory();
		model.addAttribute("list", clist);
		
		return "productForm";
	}
	
	
	

}
