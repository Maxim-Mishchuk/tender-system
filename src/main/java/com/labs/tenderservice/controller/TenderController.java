package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.tender.TenderURLConnector;
import com.labs.tenderservice.service.PropositionService;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/tender/{tenderId}")
    String createCustomTenderURL(@PathVariable("tenderId") long tenderId, String newUrl, Model model) {
        tenderService.createCustomTenderUrl(tenderId, newUrl);
        model.addAttribute("message", "tender with id: " + tenderId + ", have got the custom url");
        return "result";
    }
    @GetMapping("/tender")
    String showAllTenders(Model model) {
        List<Tender> tenderList = tenderService.getAllTenders();
        List<TenderURLConnector> urlList = tenderService.getAllURLConnectors();
        model.addAttribute("tenderList", tenderList);
        model.addAttribute("urlList", urlList);
        return "tender/tender";
    }

    @GetMapping("/tender/{tenderUrl}")
    String findTenderByURL(@PathVariable("tenderUrl") String tenderUrl, Model model) {
        Tender tender = tenderService.getTenderByURL(tenderUrl);
        List<Proposition> propositionList = propositionService.getPropositionsByTenderId(tender.getId().id());
        model.addAttribute("tender", tender);
        model.addAttribute("propositionList", propositionList);
        return "tender/tender";
    }

    @PostMapping("/tender/changeStatus")
    String changeTenderStatus(long id, String status, Model model) {
        tenderService.changeTenderStatus(id, status);
        model.addAttribute("message", "tender with id: " + id + ", was successfully changed");
        return "result";
    }

    @GetMapping("/tender/active")
    String showAllActiveTenders(Model model) {
        List<Tender> tenderList = tenderService.getActiveTenders();;
        List<TenderURLConnector> urlList = tenderService.getAllURLConnectors();
        model.addAttribute("tenderList", tenderList);
        model.addAttribute("urlList", urlList);
        return "tender/activeTender";
    }

    @GetMapping("/tender/search")
    String showTendersByKeywords(String keywords, Model model) {
        List<Tender> tenderList = tenderService.getTendersByKeywords(keywords);
        List<TenderURLConnector> urlList = tenderService.getAllURLConnectors();
        model.addAttribute("tenderList", tenderList);
        model.addAttribute("urlList", urlList);
        return "tender/tender";
    }
}
