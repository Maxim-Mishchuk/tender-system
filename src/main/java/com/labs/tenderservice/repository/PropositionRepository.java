package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.proposition.Proposition;

import java.util.List;

public interface PropositionRepository extends IRepository<Proposition>{
    List<Proposition> getPropositionsByTenderId(long id);
    Proposition updatePropositionStatus(long id, Proposition.Status status);
    List<Proposition> deletePropositionsByTenderId(long id);


}
