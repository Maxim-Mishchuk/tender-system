package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.user.User;
import com.labs.tenderservice.service.TenderService;
import com.labs.tenderservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    TenderService tenderService;

    @PostMapping("/user")
    String createUser(String username, Model model) {
        userService.createUser(username);
        model.addAttribute("message", username + " was successfully added!");
        return "result";
    }

    @GetMapping("/user/{userId}/tenders")
    String showUserTenders(@PathVariable("userId") long userId, Model model) {
        List<Tender> list = tenderService.getUserTenders(userId);
        model.addAttribute("tenderList", list);
        return "user/userTender";
    }

    @GetMapping("/user")
    String showAllUsers(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("userList", list);
        return "user/allUsers";
    }

}
