package com.udemy.com.udemy.service.impl.impl;

import com.udemy.com.udemy.entity.UserRole;
import com.udemy.com.udemy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService implements UserDetailsService {
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.udemy.com.udemy.entity.User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = buildAuthorities(user.getUserRoles());
        return buildUser(user, authorities);
    }

    private User buildUser(com.udemy.com.udemy.entity.User user, List<GrantedAuthority> authorities){

        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    //Va a convertir los roles a objetos GrantedAuthority
    private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles){
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
        for(UserRole userRole : userRoles){
            auths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<GrantedAuthority>(auths);
    }


}
