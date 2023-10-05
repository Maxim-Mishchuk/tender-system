package com.labs.tenderservice.repository;


import com.labs.tenderservice.service.Tender;
import com.labs.tenderservice.service.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TenderRepository implements TenderRepositoryInterface {

    private final List<Tender> listOfUsers= new ArrayList<>();


    @Override
    public List<Tender> getActiveTenders() {
        return null;
    }

    @Override
    public List<Tender> getTenderByKeyWords(String keywords) {
        return null;
    }

    @Override
    public List<Tender> getAllTenders() {
        return null;
    }

    @Override
    public void deleteTender(int id) {

    }

    @Override
    public void addTender(Tender tender) {

    }
}
