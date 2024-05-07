package com.ecommerce.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User,UUID>{
    Optional<User> findByuserName(String userName);
    boolean existsByuserName(String userName);
}
