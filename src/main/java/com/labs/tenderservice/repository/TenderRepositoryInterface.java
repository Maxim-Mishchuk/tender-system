package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.Tender;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TenderRepositoryInterface {
    public List<Tender> getActiveTenders();

    public List<Tender> getTenderByKeyWords(String keywords);

    public List<Tender> getAllTenders();

    public void deleteTender(int id);

    public void addTender(Tender tender);


}
