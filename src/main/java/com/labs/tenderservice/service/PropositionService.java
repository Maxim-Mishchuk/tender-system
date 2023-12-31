package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.proposition.dto.PropositionCreateDTO;
import com.labs.tenderservice.entity.proposition.dto.PropositionDTO;
import com.labs.tenderservice.exception.ResourceNotFoundException;
import com.labs.tenderservice.repository.PropositionRepository;
import com.labs.tenderservice.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PropositionService {
    private final PropositionRepository propositionRepository;
    private final TenderRepository tenderRepository;

    @Autowired
    public PropositionService(PropositionRepository propositionRepository, TenderRepository tenderRepository) {
        this.propositionRepository = propositionRepository;
        this.tenderRepository = tenderRepository;
    }

    public PropositionDTO create(PropositionCreateDTO newProposition) {
        Proposition proposition = new Proposition(
                tenderRepository.getTenderById(newProposition.getTenderId()),
                newProposition.getName(),
                newProposition.getDescription(),
                newProposition.getPrice(),
                newProposition.getCurrency(),
                Proposition.Status.NEW
        );
        if (proposition.getTender() == null) {
            throw new ResourceNotFoundException("Tender not found");
        }
        return PropositionDTO.getDTO(propositionRepository.save(proposition));
    }

    public List<PropositionDTO> getByTenderId(long tenderId) {
        return PropositionDTO.getList(propositionRepository.getPropositionsByTenderId(tenderId));
    }

    public PropositionDTO getById(long id) {
        Proposition proposition = propositionRepository.getPropositionById(id);
        checkOnNull(proposition);
        return PropositionDTO.getDTO(propositionRepository.getPropositionById(id));
    }

    public List<PropositionDTO> getAllPropositions() {
        return PropositionDTO.getList(propositionRepository.findAll());
    }

    public PropositionDTO update(PropositionDTO updatedProposition) {
        Proposition proposition = propositionRepository.getPropositionById(updatedProposition.getId());
        checkOnNull(proposition);
        proposition.setName(updatedProposition.getName());
        proposition.setDescription(updatedProposition.getDescription());
        proposition.setCurrency(updatedProposition.getCurrency());
        proposition.setStatus(updatedProposition.getStatus());
        proposition.setPrice(updatedProposition.getPrice());
        return PropositionDTO.getDTO(propositionRepository.save(proposition));
    }

    public PropositionDTO delete(long id) {
        PropositionDTO propositionToDelete = getById(id);
        propositionRepository.deleteById(id);
        return propositionToDelete;
    }

    private void checkOnNull(Proposition proposition) {
        if (proposition == null) {
            throw new ResourceNotFoundException("Proposition not found");
        }
    }
}
