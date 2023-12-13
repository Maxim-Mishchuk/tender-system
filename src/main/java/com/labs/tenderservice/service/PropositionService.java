package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.proposition.dto.PropositionCreateDTO;
import com.labs.tenderservice.entity.proposition.dto.PropositionDTO;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.repository.PropositionRepository;
import com.labs.tenderservice.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        newProposition.setStatus(Proposition.Status.ACTIVE);
        Proposition proposition = new Proposition(
                tenderRepository.getTenderById(newProposition.getTenderId()),
                newProposition.getName(),
                newProposition.getDescription(),
                newProposition.getPrice(),
                newProposition.getCurrency(),
                newProposition.getStatus()
        );
        return createPropositionDTO(propositionRepository.save(proposition));
    }

    public List<PropositionDTO> getByTenderId(long tenderId) {

        return createListOfPropositionDTO(propositionRepository.getPropositionsByTenderId(tenderId));
    }

    public PropositionDTO getById(long id) {
        return createPropositionDTO(propositionRepository.getPropositionById(id));
    }

    public List<PropositionDTO> getAllPropositions() {
        return createListOfPropositionDTO(propositionRepository.findAll());
    }

    public PropositionDTO update(PropositionDTO updatedProposition) {
        Proposition proposition = propositionRepository.getPropositionById(updatedProposition.getId());
        proposition.setName(updatedProposition.getName());
        proposition.setDescription(updatedProposition.getDescription());
        proposition.setCurrency(updatedProposition.getCurrency());
        proposition.setStatus(updatedProposition.getStatus());
        proposition.setPrice(updatedProposition.getPrice());
        return createPropositionDTO(propositionRepository.save(proposition));
    }

    public PropositionDTO delete(long id) {
        PropositionDTO propositionToDelete = getById(id);
        propositionRepository.deleteById(id);
        return propositionToDelete;
    }

    private PropositionDTO  createPropositionDTO(Proposition proposition){
        return  new PropositionDTO(
                proposition.getId(),
                proposition.getTender().getId(),
                proposition.getName(),
                proposition.getDescription(),
                proposition.getPrice(),
                proposition.getCurrency(),
                proposition.getStatus()
        );
    }
    private List<PropositionDTO> createListOfPropositionDTO(List<Proposition> propositionList){
        List<PropositionDTO>propositionDTOList = new ArrayList<>();
        for(Proposition proposition: propositionList){
            propositionDTOList.add(createPropositionDTO(proposition));
        }
        return propositionDTOList;
    }
}
