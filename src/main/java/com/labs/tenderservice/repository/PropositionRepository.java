package com.labs.tenderservice.repository;


import com.labs.tenderservice.service.Proposition;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PropositionRepository implements PropositionRepositoryInterface {
    private final List<Proposition> listOfUsers= new ArrayList<>();

    @Override
    public List<Proposition> getAllPropositionOfThisTender(int id) {
        return null;
    }

    @Override
    public void addProposition(Proposition proposition) {

    }
}
