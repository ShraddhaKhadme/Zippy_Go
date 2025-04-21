package in.shraddha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderContoller {

	@GetMapping("/place")
	public String loadForm(Model model, HttpSession session)
	{
		Integer id= (Integer)session.getAttribute("uid");
		model.addAttribute("uid", id);
		return "cart";
	}
	
	
}
