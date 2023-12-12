package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.user.User;
import com.labs.tenderservice.entity.user.dto.UserCreateDTO;
import com.labs.tenderservice.entity.user.dto.UserDTO;
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

    public UserDTO create(UserCreateDTO newUser) {
        return userRepository.save(newUser);
    }

    public UserDTO getById(long id) {
        return userRepository.getUserById(id);
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll();
    }

    public UserDTO update(UserDTO changedUser) {
        return userRepository.save(changedUser);
    }


    public UserDTO delete(long id) {
        return userRepository.deleteById(id);
    }
}
