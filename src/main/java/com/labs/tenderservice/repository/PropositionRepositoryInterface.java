package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.Proposition;

import java.util.List;

public interface PropositionRepositoryInterface  {
    public List<Proposition> getAllPropositionOfThisTender(int id);
    public void addProposition(Proposition proposition);









}
