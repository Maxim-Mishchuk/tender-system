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

    public Proposition create(Proposition newProposition) {
        return propositionRepository.create(newProposition);
    }

    public List<Proposition> getByTenderId(long tenderId) {
        return propositionRepository.getPropositionsByTenderId(tenderId);
    }

    public Proposition getById(long id) {
        return propositionRepository.read(id);
    }

    public List<Proposition> getAllPropositions() {
        return propositionRepository.getAll();
    }

    public Proposition update(Proposition updatedProposition) {
        return propositionRepository.update(updatedProposition);
    }

    public Proposition delete(long id) {
        return propositionRepository.delete(id);
    }
}
