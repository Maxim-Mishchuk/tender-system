package com.labs.tenderservice.repository;


import com.labs.tenderservice.service.Tender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TenderRepository implements TenderRepositoryInterface {

    private final List<Tender> listOfTenders = new ArrayList<>();



    @Override
    public List<Tender> getActiveTenders() {
        return getAllTenders().stream().filter(tender -> tender.getStatus()== Tender.Status.ACTIVE).toList();
    }

    @Override
    public List<Tender> getTendersByKeyWords(String keywords) {
        String[] keywordArray = keywords.toLowerCase().split("\\s+");

        return getAllTenders().
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

    @Override
    public void deleteTender(Tender tender) {
        getAllTenders().remove(tender);

    }

    @Override
    public void addTender(Tender tender) {
        getAllTenders().add(tender);
    }

    @Override
    public Tender getTender(int id) {
        return getAllTenders().get(id);
    }
}
