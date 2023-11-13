package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.user.User;
import com.labs.tenderservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    ResponseEntity<User> create(@RequestBody User newUser) {
        User user = userService.create(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getById(@PathVariable long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    ResponseEntity<Collection<User>> getAll() {
        Collection<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping
    ResponseEntity<User> update(@RequestBody User updatedUser) {
        User user = userService.update(updatedUser);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<User> delete(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }
}
