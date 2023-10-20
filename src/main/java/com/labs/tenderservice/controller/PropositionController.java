package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.proposition.Proposition;
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
    String createProposition(String name, String description, long tenderId, double price, String currency, Model model) {
        propositionService.createProposition(name, description, tenderId, price, currency);
        model.addAttribute("message", "proposition of tenderId: " + tenderId + ", with name: " + name + ", was successfully added");
        return "result";
    }

    @PostMapping("/proposition/changeStatus")
    String changePropositionStatus(long id, String status, Model model) {
        propositionService.changePropositionStatus(id, status);
        model.addAttribute("message", "proposition with id:" + id + ", was successfully changed");
        return "result";
    }

    @GetMapping("/proposition")
    String getAllPropositionsByTender(long tenderId, Model model) {
        List<Proposition> list = propositionService.getPropositionsByTenderId(tenderId);
        model.addAttribute("propositionList", list);
        return "proposition/proposition";
    }
}
