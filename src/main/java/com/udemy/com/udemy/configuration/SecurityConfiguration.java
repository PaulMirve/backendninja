package com.udemy.com.udemy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.net.Authenticator;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userService")
    private UserDetailsService userService;

    @Autowired
    public void configureGloba(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/*","/imgs/*").permitAll()
                .and().authorizeRequests().antMatchers("/contacts/add").permitAll()
                .and().authorizeRequests().antMatchers("/contacts/addUserForm").permitAll()
                .and().authorizeRequests().antMatchers("/contacts/addUser").permitAll()
        .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").loginProcessingUrl("/loginCheck")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/loginsuccess").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();
    }

}
