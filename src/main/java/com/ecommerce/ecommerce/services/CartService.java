package com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.UUID;

import com.ecommerce.ecommerce.model.Cart;

public interface CartService {
    Cart addItemToCart(String token, UUID prodId);
    String deleteItemFromCart(String token, UUID cartItemId);
    List<Cart> getAllCartItems(String token);
}
