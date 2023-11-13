package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.dto.TenderDTO;
import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import com.labs.tenderservice.repository.PropositionRepository;
import com.labs.tenderservice.repository.TenderRepository;
import com.labs.tenderservice.repository.TenderURLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class TenderService {
    private final TenderRepository tenderRepository;
    private final TenderURLRepository tenderUrlRepository;
    private final PropositionRepository propositionRepository;

    @Autowired
    public TenderService(TenderRepository tenderRepository, TenderURLRepository tenderUrlRepository, PropositionRepository propositionRepository) {
        this.tenderRepository = tenderRepository;
        this.tenderUrlRepository = tenderUrlRepository;
        this.propositionRepository = propositionRepository;
    }

    public TenderDTO create(Tender newTender) {
        newTender.setId(ID.generateID());

        if (newTender.getStatus() == null) {
            newTender.setStatus(Tender.Status.ACTIVE);
        }

        TenderUrlConnector newTenderUrlConnector = new TenderUrlConnector(
                newTender.getId(),
                String.valueOf(newTender.getId().value())
        );

        TenderUrlConnector tenderUrlConnector = tenderUrlRepository.add(newTenderUrlConnector);
        Tender tender = tenderRepository.add(newTender);

        return new TenderDTO(tender, tenderUrlConnector);
    }

    public TenderUrlConnector updateUrl(TenderUrlConnector updatedTenderUrlConnector) {
        if (
                !Objects.isNull(tenderUrlRepository.getById(updatedTenderUrlConnector.getId()))
        ) {
            return tenderUrlRepository.update(updatedTenderUrlConnector);
        }
        throw new NoSuchElementException();
    }

    public List<TenderDTO> getAll() {
        return createTenderDTOList(tenderRepository.getAll());
    }

    public List<TenderDTO> getActive() {
        return createTenderDTOList(tenderRepository.getActiveTenders());
    }

    public List<TenderDTO> getUserTenders(long userId) {
        return createTenderDTOList(tenderRepository.getUserTenders(new ID(userId)));
    }

    public List<TenderDTO> getByKeywords(String keywords) {
        return createTenderDTOList(tenderRepository.getTendersByKeywords(keywords));
    }

    public TenderDTO getById(long id) {
        Tender tender = tenderRepository.getById(new ID(id));
        return createTenderDTO(tender);
    }

    public TenderDTO getTenderByURL(String url) {
        ID tenderID = tenderUrlRepository.getTenderIdByURL(url);
        return getById(tenderID.value());
    }

    public List<TenderUrlConnector> getAllUrl() {
        return tenderUrlRepository.getAll();
    }

    public TenderDTO update(Tender updatedTender) {
        Tender tender = tenderRepository.update(updatedTender);
        return createTenderDTO(tender);
    }

    public TenderDTO delete(long id) {
        Tender tender = tenderRepository.delete(new ID(id));
        return createTenderDTO(tender);
    }

    private List<TenderDTO> createTenderDTOList(List<Tender> tenderList) {
        List<TenderDTO> tenderDTOList = new LinkedList<>();

        for (Tender tender: tenderList) {
            TenderUrlConnector url = tenderUrlRepository.getById(tender.getId());
            List<Proposition> propositions = propositionRepository.getPropositionsByTenderId(tender.getId());
            tenderDTOList.add(new TenderDTO(tender, url, propositions));
        }

        return tenderDTOList;
    }

    private TenderDTO createTenderDTO(Tender tender) {
        TenderUrlConnector tenderUrlConnector = tenderUrlRepository.getById(tender.getId());
        List<Proposition> propositions = propositionRepository.getPropositionsByTenderId(tender.getId());
        return new TenderDTO(tender, tenderUrlConnector, propositions);
    }
}