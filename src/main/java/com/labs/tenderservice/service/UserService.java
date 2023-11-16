package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.user.User;
import com.labs.tenderservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String username) {
        User newUser = new User(System.nanoTime(), username);
        userRepository.create(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }
}
