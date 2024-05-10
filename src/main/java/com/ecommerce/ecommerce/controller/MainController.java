package com.ecommerce.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.dto.ResponseStatus;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.services.AuthService;
import com.ecommerce.ecommerce.services.ProductService;
import com.ecommerce.ecommerce.services.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("api/v1")
public class MainController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping("/register/user")
    public ResponseEntity<String> resgisterUser(@Valid @RequestBody User user) {
        return new ResponseEntity<String>(authService.registration(user), HttpStatus.CREATED);
    }

    @PostMapping("/login/{username}")
    public ResponseEntity<ResponseStatus> postMethodName(@PathVariable("username") String username, @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<ResponseStatus>(authService.login(username, loginRequest), HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateByToken(@RequestHeader String token, @RequestBody User user) {
        return new ResponseEntity<User>(userService.updateUser(token, user), HttpStatus.ACCEPTED);
    }

    @PostMapping("/add/product")
    public ResponseEntity<Product> postMethodName(@RequestHeader String token, @RequestBody Product product) {
        return new ResponseEntity<Product>(productService.addProduct(token, product), HttpStatus.CREATED);
    }
    
    @GetMapping("/retreive/products")
    public ResponseEntity<List<Product>> getMethodName(@RequestHeader String token) {
        return new ResponseEntity<List<Product>>(productService.getProducts(token),HttpStatus.OK);
    }
    

}
