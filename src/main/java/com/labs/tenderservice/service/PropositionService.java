package com.labs.tenderservice.service;

import com.labs.tenderservice.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropositionService {
    private final PropositionRepository propositionRepository;

    @Autowired
    public PropositionService(PropositionRepository propositionRepository) {
        this.propositionRepository = propositionRepository;
    }


    public PropositionRepository getPropositionRepository() {
        return propositionRepository;
    }

    //getPropositionRepository()??
    public void createProposition(int id, String description, int tenderId, Double price, String name, Proposition.Status status, Proposition.Currency currency) {
        getPropositionRepository().addProposition(
                new Proposition(id, description, tenderId, price, name, status, currency)
        );
    }

    public void createProposition(String name, String description, int tenderId, double price, String currency) {
        //Logic
    }

    //getPropositionRepository()??
    //should rename: getAllPropositionsByTenderID()
    public List<Proposition> getAllProposition(int id){
        return getPropositionRepository().getListOfProposition().stream().filter(proposition -> proposition.getTenderId()==id).toList();
    }

}
