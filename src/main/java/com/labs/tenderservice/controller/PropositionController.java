package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.service.PropositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/propositions")
public class PropositionController {
    @Autowired
    PropositionService propositionService;

    @PostMapping
    ResponseEntity<Proposition> create(@RequestBody Proposition newProposition) {
        Proposition proposition = propositionService.create(newProposition);
        return ResponseEntity.status(HttpStatus.CREATED).body(proposition);
    }

    @GetMapping
    ResponseEntity<Collection<Proposition>> getAllPropositionsByTenderId(long tenderId) {
        Collection<Proposition> propositions = propositionService.getByTenderId(tenderId);
        return ResponseEntity.ok(propositions);
    }

    @GetMapping("/{id}")
    ResponseEntity<Proposition> getPropositionById(@PathVariable("id") long id) {
        Proposition proposition = propositionService.getById(id);
        if (proposition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposition);
    }

    @PutMapping
    ResponseEntity<Proposition> update(@RequestBody Proposition updatedProposition) {
        Proposition proposition = propositionService.update(updatedProposition);
        if (proposition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposition);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Proposition> delete(@PathVariable("id") long id) {
        Proposition proposition = propositionService.delete(id);
        if (proposition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposition);
    }
}
