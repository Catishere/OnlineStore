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
    public ResponseEntity deleteUser(@PathVariable( "id" ) Long id) {
        userDetailsServiceImpl.deleteById(id);
        return ResponseEntity.ok("User deleted");
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
    public ResponseEntity login(@RequestBody User user) {
        try {
            String username = user.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));

            String token = jwtTokenProvider
                    .createToken(username, userDetailsServiceImpl.loadUserByUsername(username)
                            .getRoles());

            Map<String, String> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}