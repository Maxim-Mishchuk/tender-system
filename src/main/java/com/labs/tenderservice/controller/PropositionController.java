package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.proposition.dto.PropositionCreateDTO;
import com.labs.tenderservice.entity.proposition.dto.PropositionDTO;
import com.labs.tenderservice.service.PropositionService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    ResponseEntity<PropositionDTO> getPropositionById(@PathVariable("id") long id) {
        PropositionDTO proposition = propositionService.getById(id);
        if (proposition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposition);
    }

    @PutMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    ResponseEntity<PropositionDTO> update(@RequestBody @Valid PropositionDTO updatedProposition) {
        PropositionDTO proposition = propositionService.update(updatedProposition);
        if (proposition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposition);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    ResponseEntity<PropositionDTO> delete(@PathVariable("id") long id) {
        PropositionDTO proposition = propositionService.delete(id);
        if (proposition == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proposition);
    }
}
