package com.labs.tenderservice.repository.impl;

import com.labs.tenderservice.entity.proposition.Proposition;

import java.util.List;

public interface PropositionRepository extends IRepository<Proposition>{
    List<Proposition> getPropositionByTenderId(long id, Proposition.Status status);
    Proposition updatePropositionStatus(long id, Proposition.Status status);

}
