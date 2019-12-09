package com.sap.trashproject.onlinestore.controller;

import com.sap.trashproject.onlinestore.entity.User;
import com.sap.trashproject.onlinestore.security.JwtTokenProvider;
import com.sap.trashproject.onlinestore.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public UserController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable( "id" ) Long id) {
        return userDetailsServiceImpl.deleteById(id);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userDetailsServiceImpl.findAll();
    }

    @PostMapping("/users/register")
    public User registerUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        userDetailsServiceImpl.save(user);
        return user;
    }

    @PostMapping("/users/authenticate")
    public User login(@RequestBody User user) {
        try {
            String username = user.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
            User authenticatedUser = userDetailsServiceImpl.loadUserByUsername(username);
            String token = jwtTokenProvider
                    .createToken(username, authenticatedUser
                            .getRoles());

            authenticatedUser.setToken(token);
            authenticatedUser.setPassword("");
            return authenticatedUser;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}