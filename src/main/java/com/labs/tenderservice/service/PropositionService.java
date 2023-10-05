package com.labs.tenderservice.service;

import com.labs.tenderservice.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
