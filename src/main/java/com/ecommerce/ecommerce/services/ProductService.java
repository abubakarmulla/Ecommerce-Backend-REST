package com.ecommerce.ecommerce.services;

import java.util.List;

import com.ecommerce.ecommerce.model.Product;

public interface ProductService {
    Product addProduct(String token, Product product);
    List<Product> getProducts(String token);
}
