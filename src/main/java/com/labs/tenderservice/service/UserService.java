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
        User user = new User(
                newUser.getUsername()
        );
        user = userRepository.save(user);
        return UserDTO.getDTO(user);
    }

    public UserDTO getById(long id) {
        return UserDTO.getDTO(userRepository.getUserById(id));
    }

    public List<UserDTO> getAll() {
        List<User> userList = userRepository.findAll();
        return UserDTO.getList(userList);
    }

    public UserDTO update(UserDTO changedUser) {
        User user = userRepository.getUserById(changedUser.getId());
        user.setUsername(changedUser.getUsername());
        return UserDTO.getDTO(userRepository.save(user));
    }

    public UserDTO delete(long id) {
        UserDTO user = getById(id);
        userRepository.deleteById(id);
        return user;
    }
}
