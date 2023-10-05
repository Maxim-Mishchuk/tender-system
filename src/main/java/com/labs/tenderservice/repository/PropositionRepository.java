package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.Proposition;

import java.util.List;

public interface PropositionRepository {
    List<Proposition> getAllPropositionOfThisTender(int id);
    void addProposition(String name, String description, long tenderId, double price, Proposition.Currency currency);

    List<Proposition> getListOfProposition();

}
