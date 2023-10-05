package com.labs.tenderservice.repository;

import com.labs.tenderservice.service.Tender;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TenderRepository {
    List<Tender> getActiveTenders();

    List<Tender> getTendersByKeyWords(String keywords);

    List<Tender> getAllTenders();
    //delete with id
    void deleteTender(long id);

    void addTender(long userId, String description, String name);

    Tender getTender(long id);



}
