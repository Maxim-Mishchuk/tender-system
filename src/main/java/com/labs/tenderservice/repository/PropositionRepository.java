package com.labs.tenderservice.repository;


import com.labs.tenderservice.service.Proposition;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PropositionRepository implements PropositionRepositoryInterface {
    private final List<Proposition> listOfProposition= new ArrayList<>();


    public List<Proposition> getListOfProposition() {
        return listOfProposition;
    }

    @Override
    public List<Proposition> getAllPropositionOfThisTender(int id) {
        return getListOfProposition().stream().filter(proposition -> proposition.getTenderId()==id).toList();
    }

    @Override
    public void addProposition(Proposition proposition) {
        getListOfProposition().add(proposition);
    }
}
