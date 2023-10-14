package com.labs.tenderservice.service;

import com.labs.tenderservice.repository.PropositionRepository;
import com.labs.tenderservice.repository.impl.ram.RAMPropositionRepository;
import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.proposition.Proposition;
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

    public Proposition createProposition(String name, String description, long tenderId, double price, String currency) {
        Proposition newProposition = new Proposition(
                ID.generateID(),
                new ID(tenderId),
                name,
                description,
                price,
                Proposition.Currency.valueOf(currency),
                Proposition.Status.ACTIVE
        );
        return propositionRepository.add(newProposition);
    }

    public List<Proposition> getPropositionsByTenderId(long tenderId) {
        return propositionRepository.getPropositionsByTenderId(new ID(tenderId));
    }
}
