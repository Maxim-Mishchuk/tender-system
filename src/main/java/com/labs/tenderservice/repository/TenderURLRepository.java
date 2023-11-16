package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderURLConnector;

import java.util.List;

public interface TenderURLRepository extends IRepository<TenderURLConnector> {
    long getTenderIdByURL(String URL);
}
