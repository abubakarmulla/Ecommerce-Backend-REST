package com.ecommerce.ecommerce.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.JwtTokenUtil;
import com.ecommerce.ecommerce.exceptions.AuthorizationException;
import com.ecommerce.ecommerce.exceptions.ResourceNotFoundException;
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

    @Override
    public List<Product> getProducts(String token) {
        if (tokenObj.verifyToken(token)) {
            return productRepository.findAll();
        } else {
            throw new AuthorizationException("Invalid token, Login again");
        }
    }

    @Override
    public String deleteProduct(String token, UUID prodId) {
        if (tokenObj.verifyToken(token)) {
            if (productRepository.existsById(prodId)){
                productRepository.deleteById(prodId);
                return String.format("Product with ID: %s deleted successfully!",prodId.toString());
            }
            else{
                throw new ResourceNotFoundException("Product ID","prodId",prodId);
            }
        } else {
            throw new AuthorizationException("Invalid token, Login again");
        }
    }
    
}
