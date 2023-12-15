package com.labs.tenderservice.controller;

import com.labs.tenderservice.entity.tender.dto.TenderCreateDTO;
import com.labs.tenderservice.entity.tender.dto.TenderDTO;
import com.labs.tenderservice.entity.tender.dto.TenderUrlConnectorDTO;
import com.labs.tenderservice.service.TenderService;
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
@RequestMapping("/tenders")
public class TenderController {
    @Autowired
    TenderService tenderService;

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    })
    ResponseEntity<TenderDTO> create(@RequestBody @Valid TenderCreateDTO newTender) {
        TenderDTO tender = tenderService.create(newTender);
        return ResponseEntity.status(HttpStatus.CREATED).body(tender);
    }

    @PutMapping("/tenderUrl")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    ResponseEntity<TenderUrlConnectorDTO> updateUrl(@RequestBody @Valid TenderUrlConnectorDTO tenderUrlConnector) {
        TenderUrlConnectorDTO tenderUrlConnectorDTO = tenderService.updateUrl(tenderUrlConnector);
        return ResponseEntity.ok(tenderUrlConnectorDTO);
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    ResponseEntity<TenderDTO> findTenderByURL(@PathVariable("tenderUrl") String tenderUrl) {
        TenderDTO tender = tenderService.getTenderByURL(tenderUrl);
        if (tender == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tender);
    }

    @PutMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    ResponseEntity<TenderDTO> update(@RequestBody @Valid TenderDTO updatedTender) {
        TenderDTO tenderDTO = tenderService.update(updatedTender);
        if (tenderDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenderDTO);
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    })
    ResponseEntity<TenderDTO> delete(@PathVariable("id") long id) {
        TenderDTO tender = tenderService.delete(id);
        if (tender == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tender);
    }

}