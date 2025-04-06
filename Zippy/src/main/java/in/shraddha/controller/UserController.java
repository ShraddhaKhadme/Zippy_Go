package in.shraddha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.shraddha.entity.User;
import in.shraddha.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/")
	public String loadPage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showForm()
	{
		return "register";
	}
	
	@GetMapping("/forgot")
	public String forgot()
	{
		return "forgot";
	}
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
/*	@PostMapping("/userregister")
	public String save(@ModelAttribute User u,Model model)
	{
		Integer uid=service.saveUser(u);
		String message="user data updated";
		model.addAttribute("message",message);
		return "register";
		
	}*/
	
	@PostMapping("userregister")
	public String userRegister(@ModelAttribute User u, Model model) {
		//dude each and every time the model attribute is used the register 
		//Each registration form submission creates a new instance of RegisterEntity.
		//This object is only populated with the details entered by the current user.
		//No other user's data is involved because:
	//	This is a POST request handling only one user's data at a time.--vvimp
	//	The RegisterEntity object is not shared between users; it's specific to this request.
		boolean exist = service.checkUser(u.getEmail());
		//using the getter method to fetch the details of the users
		String page = "";
		if (exist == false) {
			Integer uid = service.saveUser(u);
			String uname = u.getName();

			if (uid > 0) {
				model.addAttribute("message", uname + " Registered Succesfully with id :" + uid);
				page = "login";
				//the variable which is taken up is used here
			} else {
				model.addAttribute("message", "Register UnSuccesfull");
				page = "register";
			}
		} else {

			model.addAttribute("message", "Registration UnSuccesfull");
			page = "register";

		}
		return page;
	}
	
	/*@PostMapping("/userlogin")
	public String login(@Requestparam String name,String password,Model model)
	{
		
	}*/
	
	@PostMapping("userlogin")
	public String userLogin(User u, HttpSession session, Model model) {
		String page = "";
		String status = service.loginUser(u.getEmail(), u.getPassword(), session);
//this will get the current name and the passoword of the user who has entered
		if (status.equals("success")) {

			//the status is set in the logic of the service the value of the status is returned as the success and the failure
			model.addAttribute("uname", session.getAttribute("uname"));
			model.addAttribute("umail", session.getAttribute("umail"));
			model.addAttribute("uphone", session.getAttribute("uphone"));

			if (u.getEmail().equals("admin@gmail.com") && (u.getPassword().equals("12345678"))) {
				System.out.println("Admin login Succesfull..");
				page = "Admin";
			} else {

				System.out.println("User login sucessful..");
				page = "Home";
			}

		}

		else {
			model.addAttribute("message", "Login failed");
			System.out.println("Login failed..");
			page = "login";
		}
		return page;
	}

/*	@PostMapping("forgot")
	public String forgotPass(User u, Model model) {
		String page = "";
		System.out.println(u.getEmail());
		String result = service.forgotPassword(u.getEmail(), u.getPassword());
		if (result.equals("success")) {
			model.addAttribute("message", "Password changed succesfully");
			page = "login";
		} else {
			model.addAttribute(("message"), "No such user email");
			page = "forgot";
		}
		return page;
	}*/

}
