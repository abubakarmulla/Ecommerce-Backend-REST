package com.ecommerce.ecommerce.services.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.JwtTokenUtil;
import com.ecommerce.ecommerce.dto.LoginRequest;
import com.ecommerce.ecommerce.dto.ResponseStatus;
import com.ecommerce.ecommerce.exceptions.DuplicateResourceException;
import com.ecommerce.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil tokenObj;

    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    @Override
    public String registration(User user) {
        if (userRepository.existsByuserName(user.getUserName())){
            throw new DuplicateResourceException("User Name", "userName", user.getUserName());
        }
        else{
            user.setUserPwd(hashPassword(user.getUserPwd()));
            userRepository.save(user);
            return "Registration Successful!";
        }
    }

    @Override
    public ResponseStatus login(String username, LoginRequest loginRequest) {
        if (userRepository.existsByuserName(username)){
            User retrivedUser = userRepository.findByuserName(username).get();
            ResponseStatus response = new ResponseStatus();
            if (BCrypt.checkpw(loginRequest.getPassword(), retrivedUser.getUserPwd())){
                String token = tokenObj.generateToken(username);
                response.setToken(token);
                response.setMessage("Login Successful!");
                return response;
            }
            else{
                response.setMessage("Invalid password entered!!");
                response.setToken(null);
                return response;
            }
        }
        else{
            throw new ResourceNotFoundException("User name", "userName", username);
        }
    }
    
}
