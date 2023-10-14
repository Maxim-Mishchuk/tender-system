package com.labs.tenderservice.repository.impl.ram;


import com.labs.tenderservice.repository.TenderRepository;
import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.tender.Tender;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RAMTenderRepository extends RAMRepository<Tender> implements TenderRepository {

    @Override
    public List<Tender> getActiveTenders() {
        return repository.values().stream()
                .filter(tender -> tender.getStatus() == Tender.Status.ACTIVE)
                .toList();
    }

    @Override
    public List<Tender> getTendersByKeywords(String keywords) {
        return repository.values().stream()
                .filter(tender -> tender.getName().contains(keywords))
                .toList();
    }

    @Override
    public Tender updateTenderStatus(ID id, Tender.Status status) {
        Tender tender = repository.get(id);
        tender.setStatus(status);
        return tender;
    }
}
