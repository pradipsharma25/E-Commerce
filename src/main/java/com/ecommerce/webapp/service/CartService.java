package com.ecommerce.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.webapp.model.CartItem;
import com.ecommerce.webapp.repository.CartRepository;

@Service
public class CartService {

	@Autowired
    private CartRepository cartRepository;

    public List<CartItem> getCartItems() {
        return cartRepository.findAll();
    }

    public void addToCart(CartItem item) {
        cartRepository.save(item);
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }

    public void updateQuantity(Long id, int quantity) {
        CartItem item = cartRepository.findById(id).orElse(null);
        if (item != null) {
            item.setQuantity(quantity);
            cartRepository.save(item);
        }
    }
}
