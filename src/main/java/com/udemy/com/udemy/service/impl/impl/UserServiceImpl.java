package com.udemy.com.udemy.service.impl.impl;

import com.udemy.com.udemy.component.UserConverter;
import com.udemy.com.udemy.entity.User;
import com.udemy.com.udemy.model.UserCredential;
import com.udemy.com.udemy.repository.UserRepository;
import com.udemy.com.udemy.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("userConverter")
    private UserConverter userConverter;

    @Override
    public UserCredential addUser(UserCredential userCredential) {
        User temp= userConverter.convertUserModel2User(userCredential);
        User user = userRepository.save(temp);
        return userConverter.convertUser2UserModel(user);
    }
}
