package com.labs.tenderservice.repository.impl.ram;

import com.labs.tenderservice.repository.TenderURLRepository;
import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RAMTenderURL extends RAMRepository<TenderUrlConnector> implements TenderURLRepository {
    @Override
    public ID getTenderIdByURL(String URL) {
        List<ID> idList = repository.values().stream()
                .filter(tenderURLConnector -> tenderURLConnector.getUrl().equals(URL))
                .map(TenderUrlConnector::getId)
                .toList();
        if (idList.size() != 1) {
            return null;
        }
        return idList.get(0);
    }

    @Override
    public void setNewUrl(ID tenderId, String url) {
        if (repository.containsKey(tenderId)) {
            repository.get(tenderId).setUrl(url);
        }
    }
}