package com.ecommerce.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.webapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
}
