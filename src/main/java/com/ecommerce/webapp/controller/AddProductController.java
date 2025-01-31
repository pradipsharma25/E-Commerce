package com.ecommerce.webapp.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.webapp.model.Product;
import com.ecommerce.webapp.repository.ProductRepository;

@Controller
public class AddProductController {

	@Autowired
	private ProductRepository pRepo;	
	
	@GetMapping("/add")
	public String getsignup() {
		return "addProduct";
		
	}
	
	@PostMapping("/add")
	public String postSignup(@ModelAttribute Product product, Model model, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttribute) {
			
		try {
            // Handle file upload
            byte[] imgByte = file.getBytes();
            String imgString = Base64.getEncoder().encodeToString(imgByte);
            product.setPhoto(imgString);

            // Save the product
            pRepo.save(product);

            // Success message
            redirectAttribute.addFlashAttribute("success", "Product added successfully!");
            return "redirect:/add";
        } catch (IOException e) {
            // Handle file upload error
            model.addAttribute("errorpwd", "File not found or incorrect format.");
            return "add";
        } catch (ObjectOptimisticLockingFailureException e) {
            // Handle concurrent update error
            model.addAttribute("error", "The product was updated or deleted by another user. Please try again.");
            return "addProduct";
        }
	}
}