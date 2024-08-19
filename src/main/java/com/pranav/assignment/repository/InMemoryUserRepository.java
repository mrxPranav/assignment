package com.pranav.assignment.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.pranav.assignment.modal.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUserRepository {
    private Map<String, User> users = new HashMap<>();

    public User findByUsername(String username) {
        return users.get(username);
    }

    public User save(User user) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	user.setPassword(encoder.encode(user.getPassword()));
        users.put(user.getUsername(), user);
        return users.get(user.getUsername());
    }

    public void delete(String username) {
        users.remove(username);
    }

    public Map<String, User> findAll() {
        return users;
    }
}

