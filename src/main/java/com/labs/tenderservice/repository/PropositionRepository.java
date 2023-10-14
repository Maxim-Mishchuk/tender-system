package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.proposition.Proposition;

import java.util.List;

public interface PropositionRepository extends IRepository<Proposition> {
    List<Proposition> getPropositionsByTenderId(ID tenderId);
}
