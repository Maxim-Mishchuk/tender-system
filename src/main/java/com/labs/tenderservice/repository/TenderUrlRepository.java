package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.tender.TenderUrlConnector;

public interface TenderUrlRepository extends IRepository<TenderUrlConnector> {
    long getTenderIdByURL(String URL);
}
