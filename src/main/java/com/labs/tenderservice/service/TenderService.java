package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderURLConnector;
import com.labs.tenderservice.repository.TenderRepository;
import com.labs.tenderservice.repository.TenderURLRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;


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
                System.nanoTime(),
                userId,
                name,
                description,
                Tender.Status.ACTIVE
        );

        TenderURLConnector newTenderURLConnector = new TenderURLConnector(
                newTender.getId(),
                String.valueOf(newTender.getId())
        );

        tenderURLRepository.create(newTenderURLConnector);
        return tenderRepository.create(newTender);
    }

    public void createCustomTenderUrl(long tenderId, String newUrl) {
        if (
                !Objects.isNull(tenderURLRepository.read(tenderId))
        ) {
            tenderURLRepository.update(new TenderURLConnector(tenderId, newUrl));
        }
    }

    public List<Tender> getAllTenders() {
        return tenderRepository.getAll();
    }

    public List<Tender> getActiveTenders() {
        return tenderRepository.getActiveTenders();
    }

    public List<Tender> getUserTenders(long userId) {
        return tenderRepository.getUserTenders(userId);
    }

    public List<Tender> getTendersByKeywords(String keywords) {
        return tenderRepository.getTendersByKeywords(keywords);
    }

    public Tender getTenderById(long id) {
        return tenderRepository.read(id);
    }

    public Tender getTenderByURL(String url) {
        return tenderRepository.read(tenderURLRepository.getTenderIdByURL(url));
    }

    public List<TenderURLConnector> getAllURLConnectors() {
        return tenderURLRepository.getAll();
    }

    public Tender changeTenderStatus(long id, String status) {
        return tenderRepository.updateTenderStatus(id, Tender.Status.valueOf(status));
    }
}
