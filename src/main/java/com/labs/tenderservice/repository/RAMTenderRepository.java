package com.labs.tenderservice.repository;


import com.labs.tenderservice.service.Tender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RAMTenderRepository implements TenderRepository {

    private final List<Tender> listOfTenders = new ArrayList<>();


    @Override
    public List<Tender> getActiveTenders() {
        return listOfTenders.stream().filter(tender -> tender.getStatus()== Tender.Status.ACTIVE).toList();
    }


    @Override
    public List<Tender> getTendersByKeyWords(String keywords) {
        String[] keywordArray = keywords.toLowerCase().split("\\s+");

        return listOfTenders.
                stream().
                filter(tender -> containsAnyKeyword(tender, keywordArray))
                .collect(Collectors.toList());
    }
    private boolean containsAnyKeyword(Tender tender, String[] keywords) {
        String name = tender.getName().toLowerCase();
        String description = tender.getDescription().toLowerCase();

        for (String keyword : keywords) {
            if (name.contains(keyword) || description.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public List<Tender> getAllTenders() {
        return listOfTenders;
    }

    //delete with id
    @Override
    public void deleteTender(long id) {
        listOfTenders.remove(getTender(id));

    }


    @Override
    public void addTender(long userId, String description, String name) {
        listOfTenders.add(new Tender(System.nanoTime(), userId, description, name, Tender.Status.ACTIVE));
    }


    @Override
    public Tender getTender(long id) {
        return listOfTenders.stream().filter(tender -> tender.getId()==id).findFirst().orElse(null);
    }
}
