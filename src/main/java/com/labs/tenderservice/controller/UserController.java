package com.labs.tenderservice.controller;

import com.labs.tenderservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping("/user")
    String createUser(String username) {
        userService.createUser(username);
        return "user/user";
    }
}
