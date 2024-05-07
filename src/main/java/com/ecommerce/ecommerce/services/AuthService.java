package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.dto.ResponseStatus;
import com.ecommerce.ecommerce.model.User;

public interface AuthService {
    String registration(User user);
    ResponseStatus login(String username, LoginRequest loginRequest);
}
