package com.sap.trashproject.onlinestore.controller;

import com.sap.trashproject.onlinestore.entity.User;
import com.sap.trashproject.onlinestore.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final AuthenticationManager authenticationManager;

    public UserController(UserDetailsServiceImpl userDetailsServiceImpl,
                          AuthenticationManager authenticationManager) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.authenticationManager = authenticationManager;
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity deleteUser(@PathVariable( "id" ) Long id) {
        userDetailsServiceImpl.deleteById(id);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/admin/users")
    public List<User> getUsers() {
        return userDetailsServiceImpl.findAll();
    }

    @GetMapping("/public/users/username/{username}")
    public User getUsers(@PathVariable( "username" ) String username) {
        User user = userDetailsServiceImpl.loadUserByUsername(username);
        System.out.println(user);
        return user;
    }

    @PostMapping("/admin/users")
    public User addUser(@RequestBody User user) {
        String username = user.getUsername();
        System.out.println(username);
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        User userDb;
        try {
            userDb = userDetailsServiceImpl.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            userDetailsServiceImpl.save(user);
            user.setPassword("");
            return user;
        }
        userDb.setUsername(null);
        return userDb;
    }

    @PostMapping("/public/users/register")
    public User registerUser(@RequestBody User user) {
        String username = user.getUsername();
        System.out.println(username);
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        User userDb;
        try {
            userDb = userDetailsServiceImpl.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            user.getRoles().add("ROLE_USER");
            userDetailsServiceImpl.save(user);
            user.setPassword("");
            return user;
        }
        return userDb;
    }

    @PostMapping("/user/authenticate")
    public User login(@RequestBody User user) {
        User userDb = userDetailsServiceImpl.loadUserByUsername(user.getUsername());
        if (userDb != null)
            userDb.setPassword("");
        return userDb;
    }
}