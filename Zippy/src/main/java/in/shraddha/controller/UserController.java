package in.shraddha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.shraddha.entity.Category;
import in.shraddha.entity.Product;
import in.shraddha.entity.SubCategory;
import in.shraddha.entity.User;
import in.shraddha.service.AddCategory;
import in.shraddha.service.SubCategoryService;
import in.shraddha.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AddCategory cservice;

    @Autowired
    private SubCategoryService sservice;

    @GetMapping("/")
    public String loadPage() {
        return "index";
    }

    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    @GetMapping("/forgot")
    public String forgot() {
        return "forgot";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/userregister")
    public String userRegister(@ModelAttribute User u, Model model) {
        boolean exist = service.checkUser(u.getEmail());
        String page = "";

        if (!exist) {
            Integer uid = service.saveUser(u);
            String uname = u.getName();

            if (uid > 0) {
                model.addAttribute("message", uname + " Registered Successfully with ID: " + uid);
                page = "login";
            } else {
                model.addAttribute("message", "Registration Unsuccessful");
                page = "register";
            }
        } else {
            model.addAttribute("message", "Email already registered");
            page = "register";
        }
        return page;
    }

    @PostMapping("/Home")
    public String userLogin(@ModelAttribute User u, HttpSession session, Model model) {
        String page = "";
        String status = service.loginUser(u.getEmail(), u.getPassword(), session);

        if (status.equals("success")) {
            model.addAttribute("uname", session.getAttribute("uname"));
            model.addAttribute("umail", session.getAttribute("umail"));
            model.addAttribute("uphone", session.getAttribute("uphone"));

            if (u.getEmail().equals("admin@gmail.com")) {
                System.out.println("Admin login successful");
                page = "adminHome";
            } else {
                System.out.println("User login successful");

                List<Product> plist = service.getAllProducts();
                List<Category> clist = cservice.getAllCategories();
                List<SubCategory> slist = sservice.getAllSubCategories();

                model.addAttribute("list", plist);
                model.addAttribute("clist", clist);
                model.addAttribute("slist", slist);

                page = "UserHome";
            }
        } else {
            model.addAttribute("message", "Login failed");
            System.out.println("Login failed..");
            page = "login";
        }
        return page;
    }

    // ✅ NEW: Handles direct GET request to "/user/Home"
    @GetMapping("/Home")
    public String userHome(Model model, HttpSession session) {
        String uname = (String)session.getAttribute("uname");
        String umail = (String)session.getAttribute("umail");
        Long uphone = (Long)session.getAttribute("uphone");

        String page="";
        if (uname == null || umail == null) {
            model.addAttribute("message", "Please login first.");
            return "login";
        }
        

        model.addAttribute("uname", uname);
        model.addAttribute("umail", umail);
        model.addAttribute("uphone", uphone);
        
        if (umail.equals("admin@gmail.com")) {
            System.out.println("Admin login successful");
            page = "adminHome";
        } else {

        List<Product> plist = service.getAllProducts();
        List<Category> clist = cservice.getAllCategories();
        List<SubCategory> slist = sservice.getAllSubCategories();

        model.addAttribute("list", plist);
        model.addAttribute("clist", clist);
        model.addAttribute("slist", slist);

        page= "UserHome";
        }
        
        return page;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        System.out.println("Logged out...");
        model.addAttribute("message", "Successfully Logged Out!");
        return "login";
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> getMethodName(@RequestParam String email) {
        boolean check = service.checkUser(email);
        return ResponseEntity.ok(check);
    }

    @GetMapping("/userpage")
    public String userpage(User u, HttpSession session, Model model) {
        return "UserHomee";
    }

    // Admin - Add category
    @PostMapping("/")
    public String adminAddCategory() {
        return "addcategory";
    }

    // Admin - view all users
    @GetMapping("/allusers")
    public String viewAllUsers(@RequestParam(required = false) String message, Model model) {
        List<User> list = service.getAllUser();
        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("list", list);
        return "viewAllUsers";
    }

    // Admin - delete user
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        service.delete(id);
        return "redirect:allusers?message=User " + id + " deleted successfully";
    }
}
