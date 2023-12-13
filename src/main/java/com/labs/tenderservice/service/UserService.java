package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.user.User;
import com.labs.tenderservice.entity.user.dto.UserCreateDTO;
import com.labs.tenderservice.entity.user.dto.UserDTO;
import com.labs.tenderservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        User user1 = userRepository.save(user);
        return createUserDTO(user1);
    }

    public UserDTO getById(long id) {
        return createUserDTO(userRepository.getUserById(id));
    }

    public List<UserDTO> getAll() {
        List<User>userList = userRepository.findAll();
        return createListOFUserDTO(userList);
    }

    public UserDTO update(UserDTO changedUser) {
        User user = userRepository.getUserById(changedUser.getId());
        user.setUsername(changedUser.getUsername());
        return createUserDTO(userRepository.save(user));
    }


    public UserDTO delete(long id) {
        UserDTO user = getById(id);

        userRepository.deleteById(id);
        return user;
    }

    private UserDTO createUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getTenders()
        );
    }
    private  List<UserDTO> createListOFUserDTO(List<User>userList){
        List<UserDTO> listOfUsersDTO = new ArrayList<>();
        for(User user: userList){
            listOfUsersDTO.add(createUserDTO(user));
        }
        return listOfUsersDTO;
    }
}
