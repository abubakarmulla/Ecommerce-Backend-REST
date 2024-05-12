package com.ecommerce.ecommerce.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.User;

public interface CartRepository extends JpaRepository<Cart,UUID>{
    Optional<List<Cart>> findAllByuserId(User userId);
}
