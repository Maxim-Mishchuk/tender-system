package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RAMUserRepository implements UserRepository {
    private final List<User> listOfUsers= new ArrayList<>();



    @Override
    public User getUserById(long id) {
        return listOfUsers.stream().filter(user -> user.getId()==id).findFirst().orElse(null);
    }


    @Override
    public List<User> getAllUsers() {
        return listOfUsers;
    }

    @Override
    public void createUser(String username) {
        listOfUsers.add(new User(System.nanoTime(), username));
    }
}
