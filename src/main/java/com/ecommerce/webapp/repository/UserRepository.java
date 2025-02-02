package com.ecommerce.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.webapp.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByEmailAndPassword(String email, String password);
}
