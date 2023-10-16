package com.labs.tenderservice.repository.impl.ram;

import com.labs.tenderservice.repository.TenderURLRepository;
import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.tender.TenderURLConnector;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RAMTenderURL extends RAMRepository<TenderURLConnector> implements TenderURLRepository {
    @Override
    public ID getTenderIdByURL(String URL) {
        List<ID> idList = repository.values().stream()
                .filter(tenderURLConnector -> tenderURLConnector.getURL().equals(URL))
                .map(TenderURLConnector::getTenderID)
                .toList();
        if (idList.size() != 1) {
            return null;
        }
        return idList.get(0);
    }

    @Override
    public void setNewUrl(ID tenderId, String url) {
        if (repository.containsKey(tenderId)) {
            repository.get(tenderId).setURL(url);
        }
    }
}