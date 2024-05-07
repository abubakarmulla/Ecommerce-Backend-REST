package com.ecommerce.ecommerce.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Cart;

public interface CartRepository extends JpaRepository<Cart,UUID>{
    
}
