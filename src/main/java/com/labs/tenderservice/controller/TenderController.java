package com.labs.tenderservice.controller;

import com.labs.tenderservice.service.Proposition;
import com.labs.tenderservice.service.PropositionService;
import com.labs.tenderservice.service.Tender;
import com.labs.tenderservice.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class TenderController {
    @Autowired
    TenderService tenderService;

    @Autowired
    PropositionService propositionService;

    @PostMapping("/tender")
    String createTender(String name, String description, long userId, Model model) {
        tenderService.createTender(name, description, userId);
        model.addAttribute("message", "tender with name: " + name + ", was successfully added");
        return "result";
    }

    @GetMapping("/tender")
    String showAllTenders(Model model) {
        List<Tender> list = tenderService.getAllTenders();
        model.addAttribute("tenderList", list);
        return "tender/tender";
    }

    @GetMapping("/tender/{tenderId}")
    String findTenderByID(@PathVariable("tenderId") long id, Model model) {
        Tender tender = tenderService.getTenderById(id);
        List<Proposition> propositionList = propositionService.getAllPropositionByTenderID(id);
        model.addAttribute("tender", tender);
        model.addAttribute("propositionList", propositionList);
        return "tender/tender";
    }

    @PutMapping("/tender/{tenderId}")
    String changeStatusOfTender(@PathVariable("tenderId") long id, String status, Model model) {
        tenderService.changeStatusOfTender(id, status);
        model.addAttribute("message", "tender with id: " + id + ", was successfully changed");
        return "result";
    }

    @GetMapping("/tender/active")
    String showAllActiveTenders(Model model) {
        List<Tender> list = tenderService.getAllActiveTenders();
        model.addAttribute("tenderList", list);
        return "tender/tender";
    }

    @GetMapping("/tender/search")
    String showTendersByKeywords(String keywords, Model model) {
        List<Tender> list = tenderService.findTenderByKeywords(keywords);
        model.addAttribute("tenderList", list);
        return "tender/tender";
    }
}
