package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepositoryInterface {
    User getUserById(int id);

    List<User> getAllUsers();

}
