package com.ecommerce.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.webapp.model.CartItem;
import com.ecommerce.webapp.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	 @Autowired
	    private CartService cartService;

	    @GetMapping
	    public String viewCart(Model model) {
	        model.addAttribute("cartItems", cartService.getCartItems());
	        return "cart";
	    }

	    @PostMapping("/add")
	    public String addToCart(@ModelAttribute CartItem item) {
	        cartService.addToCart(item);
	        return "redirect:/cart";
	    }

	    @PostMapping("/update/{id}")
	    public String updateQuantity(@PathVariable Long id, @RequestParam int quantity) {
	        cartService.updateQuantity(id, quantity);
	        return "redirect:/cart";
	    }

	    @PostMapping("/remove/{id}")
	    public String removeFromCart(@PathVariable Long id) {
	        cartService.removeFromCart(id);
	        return "redirect:/cart";
	    }
	
}
