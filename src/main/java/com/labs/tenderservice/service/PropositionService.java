package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.proposition.dto.PropositionCreateDTO;
import com.labs.tenderservice.entity.proposition.dto.PropositionDTO;
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

    public PropositionDTO create(PropositionCreateDTO newProposition) {
        newProposition.setStatus(Proposition.Status.NEW);
        return propositionRepository.save(newProposition);
    }

    public List<PropositionDTO> getByTenderId(long tenderId) {
        return propositionRepository.getPropositionsByTenderId(tenderId);
    }

    public PropositionDTO getById(long id) {
        return propositionRepository.getPropositionById(id);
    }

    public List<PropositionDTO> getAllPropositions() {
        return propositionRepository.findAll();
    }

    public PropositionDTO update(PropositionDTO updatedProposition) {
        return propositionRepository.save(updatedProposition);
    }

    public PropositionDTO delete(long id) {
        PropositionDTO propositionToDelete = getById(id);
        propositionRepository.deleteById(id);
        return propositionToDelete;
    }
}
