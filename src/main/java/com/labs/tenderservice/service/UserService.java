package com.labs.tenderservice.service;

import com.labs.tenderservice.repository.UserRepository;
import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.user.User;
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
        User newUser = new User(ID.generateID(), username);
        userRepository.add(newUser);
    }
}
