package com.sap.trashproject.onlinestore.service;

import com.sap.trashproject.onlinestore.repository.UserRepository;
import com.sap.trashproject.onlinestore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRep;

    public UserDetailsServiceImpl() {
        this.userRep = new UserRepository();
    }

    public List<User> findAll() {
        userRep.openCurrentSessionWithTransaction();
        List<User> users =  userRep.findAll();
        userRep.closeCurrentSessionWithTransaction();
        return users;
    }

    public Long count() {
        userRep.openCurrentSessionWithTransaction();
        Long count = userRep.count();
        userRep.closeCurrentSessionWithTransaction();
        return count;
    }

    public User deleteById(Long userId) {
        userRep.openCurrentSessionWithTransaction();
        User user = userRep.get(userId);
        if (user != null)
            userRep.delete(user);
        userRep.closeCurrentSessionWithTransaction();
        return user;
    }
    public void save(User user) {
        userRep.openCurrentSessionWithTransaction();
        userRep.save(user);
        userRep.closeCurrentSessionWithTransaction();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        userRep.openCurrentSessionWithTransaction();
        User user = userRep.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
        userRep.closeCurrentSessionWithTransaction();

        return user;
    }

}
