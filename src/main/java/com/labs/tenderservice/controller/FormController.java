package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.user.User;
import com.labs.tenderservice.service.PropositionService;
import com.labs.tenderservice.service.TenderService;
import com.labs.tenderservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FormController {
    @Autowired
    UserService userService;

    @Autowired
    TenderService tenderService;

    @Autowired
    PropositionService propositionService;

    @GetMapping("/user/createUser")
    String createUser() {
        return "user/createUser";
    }

    @GetMapping("/tender/createTender")
    String createTender(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("userList", list);
        return "tender/createTender";
    }

    @GetMapping("/tender/searchTender")
    String searchTender() {
        return "tender/searchTender";
    }

    @GetMapping("/tender/changeStatus")
    String changeTenderStatus(Model model) {
        List<Tender> list = tenderService.getAllTenders();
        Tender.Status[] statuses = Tender.Status.values();
        model.addAttribute("tenderList", list);
        model.addAttribute("statusList", statuses);
        return "tender/changeTenderStatus";
    }

    @GetMapping("/proposition/createProposition")
    String createProposition(Model model) {
        List<Tender> list = tenderService.getAllTenders();
        Proposition.Currency[] currencies = Proposition.Currency.values();
        model.addAttribute("tenderList", list);
        model.addAttribute("currencyList", currencies);
        return "proposition/createProposition";
    }

    @GetMapping("/proposition/changeStatus")
    String changePropositionStatus(Model model) {
        List<Proposition> list = propositionService.getAllPropositions();
        Proposition.Status[] statuses = Proposition.Status.values();
        model.addAttribute("propositionList", list);
        model.addAttribute("statusList", statuses);
        return "proposition/changePropositionStatus";
    }
}
