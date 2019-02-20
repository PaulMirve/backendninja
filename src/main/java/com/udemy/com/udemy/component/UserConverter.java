package com.udemy.com.udemy.component;

import com.udemy.com.udemy.entity.User;
import com.udemy.com.udemy.model.UserCredential;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter {
    public User convertUserModel2User(UserCredential userCredential){
        User user = new User();
        String pass = userCredential.getPassword();
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        String password = pe.encode(pass);
        user.setPassword(password);
        user.setUsername(userCredential.getUsername());
        user.setEnabled(userCredential.getEnabled());
        return user;
    }

    public UserCredential convertUser2UserModel(User user){
        UserCredential userCredential = new UserCredential();
        userCredential.setPassword(user.getPassword());
        userCredential.setUsername(user.getUsername());
        userCredential.setEnabled(user.isEnabled());
        return userCredential;
    }
}
