package com.labs.tenderservice.repository;


import com.labs.tenderservice.service.Tender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TenderRepository implements TenderRepositoryInterface {

    private final List<Tender> listOfTenders = new ArrayList<>();

    //getAllTenders()??
    @Override
    public List<Tender> getActiveTenders() {
        return getAllTenders().stream().filter(tender -> tender.getStatus()== Tender.Status.ACTIVE).toList();
    }

    //getAllTenders()??
    @Override
    public List<Tender> getTendersByKeyWords(String keywords) {
        return getAllTenders().
                stream().
                filter(tender -> tender.getName().toLowerCase().contains(keywords.toLowerCase()) || tender.getDescription().toLowerCase().contains(keywords.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tender> getAllTenders() {
        return listOfTenders;
    }

    //getAllTenders()??
    @Override
    public void deleteTender(Tender tender) {
        getAllTenders().remove(tender);

    }

    //getAllTenders()??
    @Override
    public void addTender(Tender tender) {
        getAllTenders().add(tender);
    }

    //getAllTenders()??
    //!!! List.get(int index) - index != id !!!
    @Override
    public Tender getTender(int id) {
        return getAllTenders().get(id);
    }
}
