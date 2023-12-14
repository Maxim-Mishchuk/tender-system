package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.proposition.dto.PropositionCreateDTO;
import com.labs.tenderservice.entity.proposition.dto.PropositionDTO;
import com.labs.tenderservice.service.PropositionService;
import jakarta.validation.Valid;
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
    ResponseEntity<PropositionDTO> create(@RequestBody @Valid PropositionCreateDTO newProposition) {
        PropositionDTO proposition = propositionService.create(newProposition);
        return ResponseEntity.status(HttpStatus.CREATED).body(proposition);
    }

    @GetMapping
    ResponseEntity<Collection<PropositionDTO>> getAllPropositionsByTenderId(long tenderId) {
        Collection<PropositionDTO> propositions = propositionService.getByTenderId(tenderId);
        return ResponseEntity.ok(propositions);
    }

    @GetMapping("/{id}")
    ResponseEntity<PropositionDTO> getPropositionById(@PathVariable("id") long id) {
        PropositionDTO proposition = propositionService.getById(id);
        if (proposition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposition);
    }

    @PutMapping
    ResponseEntity<PropositionDTO> update(@RequestBody @Valid PropositionDTO updatedProposition) {
        PropositionDTO proposition = propositionService.update(updatedProposition);
        if (proposition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposition);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<PropositionDTO> delete(@PathVariable("id") long id) {
        PropositionDTO proposition = propositionService.delete(id);
        if (proposition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposition);
    }
}
