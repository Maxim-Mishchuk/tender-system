package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.proposition.Proposition;
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

    public Proposition createProposition(String name, String description, long tenderId, double price, String currency) {
        Proposition newProposition = new Proposition(
                System.nanoTime(),
                tenderId,
                name,
                description,
                price,
                Proposition.Currency.valueOf(currency),
                Proposition.Status.ACTIVE
        );
        return propositionRepository.create(newProposition);
    }

    public List<Proposition> getPropositionsByTenderId(long tenderId) {
        return propositionRepository.getPropositionsByTenderId(tenderId);
    }

    public List<Proposition> getAllPropositions() {
        return propositionRepository.getAll();
    }

    public Proposition changePropositionStatus(long id, String status) {
        return propositionRepository.updatePropositionStatus(id, Proposition.Status.valueOf(status));
    }
}
