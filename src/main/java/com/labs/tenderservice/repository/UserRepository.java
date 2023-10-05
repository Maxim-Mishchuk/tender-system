package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository {
    User getUserById(long id);

    List<User> getAllUsers();

    void createUser(String username);

}
