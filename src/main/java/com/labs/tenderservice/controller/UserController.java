package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.user.dto.UserCreateDTO;
import com.labs.tenderservice.entity.user.dto.UserDTO;
import com.labs.tenderservice.service.UserService;
import jakarta.validation.Valid;
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
    ResponseEntity<UserDTO> create(@RequestBody @Valid UserCreateDTO newUser) {
        UserDTO user = userService.create(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getById(@PathVariable long id) {
        UserDTO user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    ResponseEntity<Collection<UserDTO>> getAll() {
        Collection<UserDTO> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping
    ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO updatedUser) {
        UserDTO user = userService.update(updatedUser);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<UserDTO> delete(@PathVariable long id) {
        UserDTO user =  userService.delete(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
