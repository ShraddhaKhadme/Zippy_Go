package in.shraddha.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.shraddha.entity.Category;
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
	public String loadpage(Model model)
	{
		List<Category> list=cservice.allCategory();
		model.addAttribute("list", list);
		return "productform";
	}
	
}
