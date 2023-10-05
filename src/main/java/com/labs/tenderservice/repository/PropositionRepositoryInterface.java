package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.Proposition;

import java.util.List;

public interface PropositionRepositoryInterface  {
    List<Proposition> getAllPropositionOfThisTender(int id);
    void addProposition(Proposition proposition);

}
