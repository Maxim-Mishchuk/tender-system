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

    public User create(User newUser) {
        return userRepository.create(newUser);
    }

    public User getById(long id) {
        return userRepository.read(id);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User update(User changedUser) {
        return userRepository.update(changedUser);
    }

    public void delete(long id) {
        userRepository.delete(id);
    }
}
