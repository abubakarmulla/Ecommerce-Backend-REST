package com.ecommerce.ecommerce.services.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.JwtTokenUtil;
import com.ecommerce.ecommerce.exceptions.AuthorizationException;
import com.ecommerce.ecommerce.exceptions.DuplicateResourceException;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil tokenObj;

    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    @Override
    public User updateUser(String token, User user) {
        if (tokenObj.verifyToken(token)){
                User retrievdUser = userRepository.findByuserName(tokenObj.getUserNameFromToken(token)).get();
                if (user.getUserName().equals(retrievdUser.getUserName())){
                    user.setUserId(retrievdUser.getUserId());
                    user.setUserPwd(hashPassword(user.getUserPwd()));
                    return userRepository.save(user);
                }
                else{
                    if (userRepository.existsByuserName(user.getUserName())){
                        throw new DuplicateResourceException("user name", "userName", user.getUserName());
                    }
                    else{
                    retrievdUser.setUserName(user.getUserName());
                    retrievdUser.setUserEmail(user.getUserEmail());
                    retrievdUser.setUserPhone(user.getUserPhone());
                    retrievdUser.setUserPwd(hashPassword(user.getUserPwd()));
                    return userRepository.save(retrievdUser);
                    }
                }
            }
            else{
                throw new AuthorizationException("Invalid token, Login again");
            }
    }
    
}
