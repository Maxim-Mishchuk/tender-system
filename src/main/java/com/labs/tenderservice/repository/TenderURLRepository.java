package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;

public interface TenderURLRepository extends IRepository<TenderUrlConnector> {
    ID getTenderIdByURL(String URL);
    void setNewUrl(ID tenderId, String url);
}
