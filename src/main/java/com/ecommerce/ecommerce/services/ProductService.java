package com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.UUID;

import com.ecommerce.ecommerce.model.Product;

public interface ProductService {
    Product addProduct(String token, Product product);
    List<Product> getProducts(String token);
    String deleteProduct(String token, UUID prodId);
}
