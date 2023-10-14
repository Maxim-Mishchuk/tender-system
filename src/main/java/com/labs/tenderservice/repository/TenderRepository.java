package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.tender.Tender;
import java.util.List;

public interface TenderRepository extends IRepository<Tender> {
    List<Tender> getActiveTenders();
    List<Tender> getTendersByKeywords(String keywords);
    Tender updateTenderStatus(ID id, Tender.Status status);
}
