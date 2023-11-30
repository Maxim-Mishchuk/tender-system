package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PropositionService {
    private final PropositionRepository propositionRepository;

    @Autowired
    public PropositionService(PropositionRepository propositionRepository) {
        this.propositionRepository = propositionRepository;
    }

    public Proposition create(Proposition newProposition) {
        return propositionRepository.save(newProposition);
    }

    public List<Proposition> getByTenderId(long tenderId) {
        return propositionRepository.getPropositionsByTenderId(tenderId);
    }

    public Proposition getById(long id) {
        return propositionRepository.getPropositionById(id);
    }

    public List<Proposition> getAllPropositions() {
        return propositionRepository.findAll();
    }

    public Proposition update(Proposition updatedProposition) {
        return propositionRepository.save(updatedProposition);
    }

    public Proposition delete(long id) {
        Proposition propositionToDelete = getById(id);
        propositionRepository.deleteById(id);
        return propositionToDelete;
    }
}
