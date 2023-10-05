package com.labs.tenderservice.service;

import com.labs.tenderservice.repository.PropositionRepository;
import com.labs.tenderservice.repository.RAMPropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropositionService {
    private final PropositionRepository propositionRepository;

    @Autowired
    public PropositionService(RAMPropositionRepository propositionRepository) {
        this.propositionRepository = propositionRepository;
    }




    public void createProposition(String name, String description, long tenderId, double price, String currency) {
        propositionRepository.addProposition(name, description, tenderId, price, Proposition.Currency.valueOf(currency));
    }


    public List<Proposition> getAllPropositionByTenderID(long id){
        return propositionRepository.getListOfProposition().stream().filter(proposition -> proposition.getTenderId()==id).toList();
    }

}
