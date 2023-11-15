package com.labs.tenderservice.repository.impl;

import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderURLConnector;

import java.util.List;

public interface TenderURLRepository extends IRepository<TenderURLConnector> {
    List<Tender> getActiveTenders();
    List<Tender> getUserTenders(long userId);
    List<Tender> getTendersByKeywords(String keywords);
    Tender updateTenderStatus(long id, Tender.Status status);
}
