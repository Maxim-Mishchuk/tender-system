package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.ID;
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
        newUser.setId(ID.generateID());
        return userRepository.add(newUser);
    }

    public User getById(long id) {
        return userRepository.getById(new ID(id));
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User update(User changedUser) {
        return userRepository.update(changedUser);
    }

    public User delete(long id) {
        return userRepository.delete(new ID(id));
    }
}
