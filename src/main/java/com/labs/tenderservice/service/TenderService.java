package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.dto.TenderDTO;
import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import com.labs.tenderservice.repository.PropositionRepository;
import com.labs.tenderservice.repository.TenderRepository;
import com.labs.tenderservice.repository.TenderUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class TenderService {
    private final TenderRepository tenderRepository;
    private final TenderUrlRepository tenderUrlRepository;
    private final PropositionRepository propositionRepository;

    @Autowired
    public TenderService(TenderRepository tenderRepository, TenderUrlRepository tenderUrlRepository, PropositionRepository propositionRepository) {
        this.tenderRepository = tenderRepository;
        this.tenderUrlRepository = tenderUrlRepository;
        this.propositionRepository = propositionRepository;
    }

    public TenderDTO create(Tender newTender) {

        if (newTender.getStatus() == null) {
            newTender.setStatus(Tender.Status.ACTIVE);
        }

        Tender tender = tenderRepository.create(newTender);

        TenderUrlConnector newTenderUrlConnector = new TenderUrlConnector(
                tender.getId(),
                String.valueOf(tender.getId())
        );

        TenderUrlConnector tenderUrlConnector = tenderUrlRepository.create(newTenderUrlConnector);

        return new TenderDTO(tender, tenderUrlConnector);
    }

    public TenderUrlConnector updateUrl(TenderUrlConnector updatedTenderUrlConnector) {
        if (
                !Objects.isNull(tenderUrlRepository.read(updatedTenderUrlConnector.getTenderId()))
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
        return createTenderDTOList(tenderRepository.getUserTenders(userId));
    }

    public List<TenderDTO> getByKeywords(String keywords) {
        return createTenderDTOList(tenderRepository.getTendersByKeywords(keywords));
    }

    public TenderDTO getById(long id) {
        Tender tender = tenderRepository.read(id);
        return createTenderDTO(tender);
    }

    public TenderDTO getTenderByURL(String url) {
        long id = tenderUrlRepository.getTenderIdByURL(url);
        return getById(id);
    }

    public List<TenderUrlConnector> getAllUrl() {
        return tenderUrlRepository.getAll();
    }

    public TenderDTO update(Tender updatedTender) {
        Tender tender = tenderRepository.update(updatedTender);
        return createTenderDTO(tender);
    }

    public TenderDTO delete(long id) {
        List<Proposition>  propositions = propositionRepository.deletePropositionsByTenderId(id);
        TenderUrlConnector tenderUrlConnector = tenderUrlRepository.delete(id);
        return new TenderDTO((tenderRepository.delete(id)),tenderUrlConnector, propositions);
    }

    private List<TenderDTO> createTenderDTOList(List<Tender> tenderList) {
        List<TenderDTO> tenderDTOList = new LinkedList<>();

        for (Tender tender : tenderList) {
            TenderUrlConnector url = tenderUrlRepository.read(tender.getId());
            List<Proposition> propositions = propositionRepository.getPropositionsByTenderId(tender.getId());
            tenderDTOList.add(new TenderDTO(tender, url, propositions));
        }

        return tenderDTOList;
    }

    private TenderDTO createTenderDTO(Tender tender) {
        TenderUrlConnector tenderUrlConnector = tenderUrlRepository.read(tender.getId());
        List<Proposition> propositions = propositionRepository.getPropositionsByTenderId(tender.getId());
        return new TenderDTO(tender, tenderUrlConnector, propositions);
    }
}