package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.user.User;
import com.labs.tenderservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User newUser) {
        return userRepository.save(newUser);
    }

    public User getById(long id) {
        return userRepository.getUserById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User update(User changedUser) {
        return userRepository.save(changedUser);
    }


    public User delete(long id) {
        return userRepository.deleteById(id);
    }
}
