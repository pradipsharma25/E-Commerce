package com.ecommerce.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.webapp.model.Product;
import com.ecommerce.webapp.repository.ProductRepository;
import com.ecommerce.webapp.service.ProductService;

@Controller
public class HomeController {

	@Autowired
    private ProductService productService;
	
	@Autowired
	private ProductRepository pRepo;
	

    @GetMapping("/")
    public String home(Model model, Product product) {
        model.addAttribute("products", productService.getAllProducts());
        return "home";
    }
    
    @GetMapping("/home")
    public String getHome() {
    	
    	return "redirect:/";
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
