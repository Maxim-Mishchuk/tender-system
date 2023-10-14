package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.tender.TenderURLConnector;

public interface TenderURLRepository extends IRepository<TenderURLConnector> {
    ID getTenderIdByURL(String URL);
}
