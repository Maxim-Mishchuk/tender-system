package com.labs.tenderservice.repository.impl.ram;


import com.labs.tenderservice.repository.PropositionRepository;
import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.proposition.Proposition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RAMPropositionRepository extends RAMRepository<Proposition> implements PropositionRepository {
    @Override
    public List<Proposition> getPropositionsByTenderId(ID tenderId) {
        return repository.values().stream()
                .filter(proposition -> proposition.getTenderId().equals(tenderId))
                .toList();
    }
}
