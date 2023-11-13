package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.dto.TenderDTO;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import com.labs.tenderservice.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/tenders")
public class TenderController {
    @Autowired
    TenderService tenderService;

    @PostMapping
    ResponseEntity<TenderDTO> create(@RequestBody Tender newTender) {
        TenderDTO tender = tenderService.create(newTender);
        return ResponseEntity.status(HttpStatus.CREATED).body(tender);
    }

    @PutMapping("/tenderUrl")
    ResponseEntity<TenderUrlConnector> updateUrl(@RequestBody TenderUrlConnector tenderUrlConnector) {
        TenderUrlConnector url = tenderService.updateUrl(tenderUrlConnector);
        return ResponseEntity.ok(url);
    }

    @GetMapping
    Collection<TenderDTO> getAllTenders() {
        return tenderService.getAll();

    }

    @GetMapping("/active")
    ResponseEntity<Collection<TenderDTO>> getActiveTenders() {
        Collection<TenderDTO> tenders = tenderService.getActive();
        return ResponseEntity.ok(tenders);
    }

    @GetMapping("/search")
    ResponseEntity<Collection<TenderDTO>> getTendersByKeywords(String keywords) {
        Collection<TenderDTO> tenders = tenderService.getByKeywords(keywords);
        return ResponseEntity.ok(tenders);
    }

    @GetMapping("/{tenderUrl}")
    ResponseEntity<TenderDTO> findTenderByURL(@PathVariable("tenderUrl") String tenderUrl) {
        TenderDTO tender = tenderService.getTenderByURL(tenderUrl);
        if (tender == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tender);
    }

    @PutMapping
    ResponseEntity<TenderDTO> update(@RequestBody Tender updatedTender) {
        TenderDTO tenderDTO = tenderService.update(updatedTender);
        if (tenderDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenderDTO);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<TenderDTO> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok().build();
    }

}