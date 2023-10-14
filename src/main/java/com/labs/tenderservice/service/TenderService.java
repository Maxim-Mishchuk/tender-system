package com.labs.tenderservice.service;

import com.labs.tenderservice.repository.TenderRepository;
import com.labs.tenderservice.repository.TenderURLRepository;
import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderURLConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class TenderService {
    private final TenderRepository tenderRepository;
    private final TenderURLRepository tenderURLRepository;

    @Autowired
    public TenderService(TenderRepository tenderRepository, TenderURLRepository tenderURLRepository) {
        this.tenderRepository = tenderRepository;
        this.tenderURLRepository = tenderURLRepository;
    }

    public Tender createTender(String name, String description, long userId) {
        Tender newTender = new Tender(
                ID.generateID(),
                new ID(userId),
                name,
                description,
                Tender.Status.ACTIVE
        );

        TenderURLConnector newTenderURLConnector = new TenderURLConnector(
                newTender.getId(),
                String.valueOf(newTender.getId().id())
        );

        tenderURLRepository.add(newTenderURLConnector);
        return tenderRepository.add(newTender);
    }

    public List<Tender> getAllTenders() {
        return tenderRepository.getAll();
    }

    public List<Tender> getActiveTenders() {
        return tenderRepository.getActiveTenders();
    }

    public List<Tender> getTendersByKeywords(String keywords) {
        return tenderRepository.getTendersByKeywords(keywords);
    }

    public Tender getTenderById(long id) {
        return tenderRepository.getById(new ID(id));
    }

    public Tender getTenderByURL(String url) {
        ID tenderID = tenderURLRepository.getTenderIdByURL(url);
        return tenderRepository.getById(tenderID);
    }

    public Tender changeTenderStatus(long id, String status) {
        return tenderRepository.updateTenderStatus(new ID(id), Tender.Status.valueOf(status));
    }
}
