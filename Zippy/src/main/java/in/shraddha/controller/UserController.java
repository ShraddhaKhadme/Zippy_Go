package in.shraddha.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.shraddha.entity.Category;
import in.shraddha.entity.Product;
import in.shraddha.entity.User;
import in.shraddha.service.AddCategory;
import in.shraddha.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private AddCategory cservice;
	
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
	

	
	@PostMapping("/userregister")
	public String userRegister(@ModelAttribute User u, Model model) {
		
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
	
	
	
	@PostMapping("/userlogin")
	public String userLogin(@ModelAttribute User u, HttpSession session, Model model) {
		String page = "";
		String status = service.loginUser(u.getEmail(), u.getPassword(), session);
//this will get the current name and the password of the user who has entered
		if (status.equals("success")) {

			//the status is set in the logic of the service the value of the status is returned as the success and the failure
			model.addAttribute("uname", session.getAttribute("uname"));
			model.addAttribute("umail", session.getAttribute("umail"));
			model.addAttribute("uphone", session.getAttribute("uphone"));

			if (u.getEmail().equals("admin@gmail.com") ) {
				System.out.println("Admin login Succesfull..");
				page = "adminHome";
			} else {
				
				List<Product> plist=service.getAllProducts();
				System.out.println("products"+plist.size());
				List<Category> clist=cservice.getAllCategories();
				//List<Product> plist=service.getAllProducts().stream().limit(5).collect(Collectors.toList());
				model.addAttribute("list", plist);
				model.addAttribute("clist", clist);

				System.out.println("User login sucessful..");
				page = "UserHome";
			}

		}

		else {
			model.addAttribute("message", "Login failed");
			System.out.println("Login failed..");
			page = "login";
		}
		return page;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		System.out.println("Logged out...");
		model.addAttribute("message", "Successfully Loged Out..!!");
		return "login";
	}
	

	@GetMapping("/checkEmail")
	public ResponseEntity<Boolean> getMethodName(@RequestParam String email) {
		
		boolean check=service.checkUser(email);
		
		return ResponseEntity.ok(check);
		
	}
	
	/*@PostMapping("forgot")
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
	}
	*/
	
	@GetMapping("/userpage")
	public String userpage( User u, HttpSession session, Model model )
	{
		//String message="user page";
		return "UserHomee";
	}
	/*@PostMapping("/userHome")
	public String userpage( User u,String email, HttpSession session, Model model )
	{
		User user1=service.getOneUser(id);
		model.addAttribute("user",user1);
		return "UserHome";
	}
	*/
	
	
//	Admin- Add category
	@PostMapping("/")
	public String adminAddCategory() {
		return "addcategory";
		
	}

}
