package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements UserRepositoryInterface {
    private final List<User> listOfUsers= new ArrayList<>();

    //getListOfUsers() == getAllUsers()
    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    //getListOfUsers()??
    @Override
    public User getUserById(int id) {

        return getListOfUsers().get(id);
    }

    //getListOfUsers()??
    @Override
    public List<User> getAllUsers() {
        return getListOfUsers();
    }
}
