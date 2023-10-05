package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepositoryInterface {
    public User getUserById(int id);

    public List<User> getAllUsers();

}
