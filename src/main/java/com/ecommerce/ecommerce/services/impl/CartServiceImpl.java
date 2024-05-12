package com.ecommerce.ecommerce.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.JwtTokenUtil;
import com.ecommerce.ecommerce.exceptions.AuthorizationException;
import com.ecommerce.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.CartRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.services.CartService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    JwtTokenUtil tokenObj;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart addItemToCart(String token, UUID prodId) {
        if (tokenObj.verifyToken(token)) {
            Cart cart = new Cart();
            User user = userRepository.findByuserName(tokenObj.getUserNameFromToken(token)).get();
            if (productRepository.existsById(prodId)) {
                Product product = productRepository.findById(prodId).get();
                cart.setProdId(product);
                cart.setUserId(user);
                cart.setProdName(product.getProdName());
                cart.setProdCost(product.getProdCost());
                return cartRepository.save(cart);
            } else {
                throw new ResourceNotFoundException("Product ID", "prodId", prodId);
            }
        } else {
            throw new AuthorizationException("Invalid token, Login again");
        }
    }

    @Override
    public String deleteItemFromCart(String token, UUID cartItemId) {
        if (tokenObj.verifyToken(token)) {
            if (cartRepository.existsById(cartItemId)) {
                cartRepository.deleteById(cartItemId);
                return String.format("Cart Item with ID: %s has deleted successfully", cartItemId.toString());
            }
            else{
                throw new ResourceNotFoundException("Cart Item ID", "cartItemId", cartItemId);
            }
        } else {
            throw new AuthorizationException("Invalid token, Login again");
        }
    }

    @Override
    public List<Cart> getAllCartItems(String token) {
        if (tokenObj.verifyToken(token)) {
            User user = userRepository.findByuserName(tokenObj.getUserNameFromToken(token)).get();
            return cartRepository.findAllByuserId(user).get();
        } else {
            throw new AuthorizationException("Invalid token, Login again");
        }
    }

}
