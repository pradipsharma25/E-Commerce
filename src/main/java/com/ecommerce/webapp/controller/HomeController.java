package com.ecommerce.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.webapp.model.Product;
import com.ecommerce.webapp.model.User;
import com.ecommerce.webapp.repository.ProductRepository;
import com.ecommerce.webapp.repository.UserRepository;
import com.ecommerce.webapp.service.ProductService;

@Controller
public class HomeController {

	@Autowired
    private ProductService productService;
	
	@Autowired
	private ProductRepository pRepo;
	
	@Autowired
	private UserRepository uRepo;
	

    @GetMapping("/")
    public String home() {
        
        return "login";
    }
    
    @GetMapping("/login")
    public String getLogin() {
    	
    	return "login";
    }
    @PostMapping("/login")
    public String postLogin(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
    	
    	if(uRepo.existsByEmailAndPassword(email, password)) {
    		return "redirect:/home";
    	}else {
    	redirectAttributes.addFlashAttribute("relogin","Email and Password did not match!");
    	return "redirect:/login";
    	}
    }
    
    @GetMapping("/signup")
    public String getSignup() {
    	
    	return "signup";
    }
    
    @PostMapping("/signup")
    public String postSignup(@ModelAttribute User user, Model model, @RequestParam("password") String password , @RequestParam("repassword") String repassword, RedirectAttributes redirectAttributes) {
    	if(!password.equals(repassword)) {
    		redirectAttributes.addFlashAttribute("reenter","password did not match!");
    		return "redirect:/signup";
    	}else {
    	uRepo.save(user);
    	return "redirect:/login";
    	}
    }
    
    @GetMapping("/admin")
    public String getAdmin() {
    	
    	return "admin";
    }
    
    @PostMapping("/admin")
    public String postAdmin(@RequestParam("email") String email, @RequestParam("password") String password) {
    	
    	if(email.equals("admin")&& password.equals("Admin@123")) {
    		
    		return "redirect:/add";
    	}else {
    		return "redirect:/admin";
    	}
 
    }
    
    @GetMapping("/home")
    public String getHome(Model model, Product product) {
    	model.addAttribute("products", productService.getAllProducts());
    	return "home";
    }
    
    @GetMapping("/error")
    public String error() {
    	
    	return "navbar";
    }
    
    @GetMapping("/search")
    public String getSearch(@RequestParam("search") String search, Model model) {
    	model.addAttribute("products", pRepo.findByNameContainingOrDescriptionContaining(search, search));
    	return "home";
    }
}
