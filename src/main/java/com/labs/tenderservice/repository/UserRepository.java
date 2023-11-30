package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User deleteById(long id);
    User getUserById(long id);

}