package in.shraddha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.shraddha.entity.User;
import in.shraddha.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/register")
	public String showForm()
	{
		return "register";
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@PostMapping("/userregister")
	public String save(@ModelAttribute User u,Model model)
	{
		Integer uid=service.saveUser(u);
		String message="user data updated";
		model.addAttribute("message",message);
		return "register";
		
	}
	
	
	
	

}
