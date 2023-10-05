package com.labs.tenderservice.repository;


import com.labs.tenderservice.service.Proposition;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RAMPropositionRepository implements PropositionRepository {
    private final List<Proposition> listOfProposition= new ArrayList<>();

    @Override
    public List<Proposition> getListOfProposition() {
        return listOfProposition;
    }


    @Override
    public List<Proposition> getAllPropositionOfThisTender(int id) {
        return listOfProposition.stream().filter(proposition -> proposition.getTenderId()==id).toList();
    }


    @Override
    public void addProposition(String name, String description, long tenderId, double price, Proposition.Currency currency) {
        listOfProposition.add(new Proposition(System.nanoTime(),description, tenderId, price, name, Proposition.Status.ACTIVE, currency));
    }

}
