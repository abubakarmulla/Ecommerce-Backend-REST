package com.ecommerce.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.JwtTokenUtil;
import com.ecommerce.ecommerce.exceptions.AuthorizationException;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JwtTokenUtil tokenObj;

    @Override
    public Product addProduct(String token, Product product) {
        if (tokenObj.verifyToken(token)) {
            return productRepository.save(product);
        } else {
            throw new AuthorizationException("Invalid token, Login again");
        }
    }
    
}
