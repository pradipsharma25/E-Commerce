package com.ecommerce.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.webapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
