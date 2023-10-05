package com.labs.tenderservice.controller;

import com.labs.tenderservice.service.Proposition;
import com.labs.tenderservice.service.PropositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PropositionController {
    @Autowired
    PropositionService propositionService;

    @PostMapping("/proposition")
    String createProposition(String name, String description, int tenderId, double price, String currency) {
        propositionService.createProposition(name, description, tenderId, price, currency);
        return "proposition/proposition";
    }

    @GetMapping("/proposition")
    String getAllPropositionsByTender(int tenderId, Model model) {
        List<Proposition> list = propositionService.getAllPropositionByTenderID(tenderId);
        model.addAttribute("propositionList", list);
        return "proposition/proposition";
    }
}
