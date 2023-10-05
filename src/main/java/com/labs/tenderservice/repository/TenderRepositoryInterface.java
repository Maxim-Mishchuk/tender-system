package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.Tender;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TenderRepositoryInterface {
    public List<Tender> getActiveTenders();

    public List<Tender> getTendersByKeyWords(String keywords);

    public List<Tender> getAllTenders();

    public void deleteTender(Tender tender);

    public void addTender(Tender tender);

    public Tender getTender(int id);


}
