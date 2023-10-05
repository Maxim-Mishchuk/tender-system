package com.labs.tenderservice.service;

import com.labs.tenderservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String username) {
        //Logic
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
