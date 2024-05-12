package com.ecommerce.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.dto.ResponseStatus;
import com.ecommerce.ecommerce.model.Cart;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.services.AuthService;
import com.ecommerce.ecommerce.services.CartService;
import com.ecommerce.ecommerce.services.ProductService;
import com.ecommerce.ecommerce.services.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("api/v1")
public class MainController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @PostMapping("/register/user")
    public ResponseEntity<String> resgisterUser(@Valid @RequestBody User user) {
        return new ResponseEntity<String>(authService.registration(user), HttpStatus.CREATED);
    }

    @PostMapping("/login/{username}")
    public ResponseEntity<ResponseStatus> loginUser(@PathVariable("username") String username, @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<ResponseStatus>(authService.login(username, loginRequest), HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateByToken(@RequestHeader String token, @RequestBody User user) {
        return new ResponseEntity<User>(userService.updateUser(token, user), HttpStatus.ACCEPTED);
    }

    @PostMapping("/add/product")
    public ResponseEntity<Product> addProd(@RequestHeader String token, @RequestBody Product product) {
        return new ResponseEntity<Product>(productService.addProduct(token, product), HttpStatus.CREATED);
    }
    
    @GetMapping("/retreive/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader String token) {
        return new ResponseEntity<List<Product>>(productService.getProducts(token),HttpStatus.OK);
    }
    
    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<String> deleteProd(@RequestHeader String token, @PathVariable UUID prodId){
        return new ResponseEntity<String>(productService.deleteProduct(token, prodId),HttpStatus.OK);
    }

    @PostMapping("/add/cart/{prodId}")
    public ResponseEntity<Cart> addToCart(@RequestBody String token, @PathVariable UUID prodId) {
        return new ResponseEntity<Cart>(cartService.addItemToCart(token, prodId),HttpStatus.CREATED);
    }
    
    @DeleteMapping("/cart/{cartItemId}")
    public ResponseEntity<String> deleteFromCart(@RequestHeader String token, @PathVariable UUID cartItemId){
        return new ResponseEntity<String>(cartService.deleteItemFromCart(token, cartItemId),HttpStatus.OK);
    }

    @GetMapping("/cartItems")
    public ResponseEntity<List<Cart>> getAllCart(@RequestHeader String token) {
        return new ResponseEntity<List<Cart>>(cartService.getAllCartItems(token),HttpStatus.OK);
    }
    
}
