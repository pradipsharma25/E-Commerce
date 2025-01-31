package com.ecommerce.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.webapp.model.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long>{

}
