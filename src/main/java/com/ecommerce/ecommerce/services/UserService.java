package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.model.User;

public interface UserService {
    User updateUser(String token, User user);
}
