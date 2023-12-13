package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.tender.dto.TenderCreateDTO;
import com.labs.tenderservice.entity.tender.dto.TenderDTO;
import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import com.labs.tenderservice.entity.tender.dto.TenderUrlConnectorDTO;
import com.labs.tenderservice.repository.PropositionRepository;
import com.labs.tenderservice.repository.TenderRepository;
import com.labs.tenderservice.repository.TenderUrlRepository;
import com.labs.tenderservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class TenderService {
    private final TenderRepository tenderRepository;
    private final TenderUrlRepository tenderUrlRepository;
    private final PropositionRepository propositionRepository;
    private final UserRepository userRepository;

    @Autowired
    public TenderService(TenderRepository tenderRepository, TenderUrlRepository tenderUrlRepository, PropositionRepository propositionRepository, UserRepository userRepository) {
        this.tenderRepository = tenderRepository;
        this.tenderUrlRepository = tenderUrlRepository;
        this.propositionRepository = propositionRepository;
        this.userRepository = userRepository;
    }

    public TenderDTO create(TenderCreateDTO newTender) {
        newTender.setStatus(Tender.Status.CLOSED);
        Tender tender = new Tender(
                userRepository.getUserById(newTender.getUserId()),
                newTender.getName(),
                newTender.getDescription(),
                newTender.getStatus()
                );


        TenderUrlConnector newTenderUrlConnector = new TenderUrlConnector();
        tender.setTenderUrlConnector(newTenderUrlConnector);
        Tender tender1 = tenderRepository.save(tender);

        return new TenderDTO(tender, tender.getTenderUrlConnector());
    }

    public TenderUrlConnectorDTO updateUrl(TenderUrlConnectorDTO updatedTenderUrlConnector) {
        TenderUrlConnector tenderUrlConnector =
                tenderUrlRepository.getTenderUrlConnectorByTenderId(updatedTenderUrlConnector.getTenderId());
                tenderUrlConnector.setUrl(updatedTenderUrlConnector.getUrl());
        return createTenderUrlConnectorDTO(tenderUrlRepository.save(tenderUrlConnector));
    }

    public List<TenderDTO> getAll() {
        List<Tender> tenders = tenderRepository.findAll();
        for (Tender tender : tenders) {
            System.out.println(tender.getTenderUrlConnector());
        }
        return createTenderDTOList(tenders);
    }

    public List<TenderDTO> getActive() {
        return createTenderDTOList(tenderRepository.getActiveTenders());
    }


    public List<TenderDTO> getByKeywords(String keywords) {
        return createTenderDTOList(tenderRepository.getTendersByKeywords(keywords));
    }

    public TenderDTO getById(long id) {
        Tender tender = tenderRepository.getReferenceById(id);
        return createTenderDTO(tender);
    }

    public TenderDTO getTenderByURL(String url) {
        Tender tender = tenderUrlRepository.getTenderByUrl(url);
        return getById(tender.getId());
    }

    public List<TenderUrlConnector> getAllUrl() {
        return tenderUrlRepository.findAll();
    }

    public TenderDTO update(TenderDTO updatedTender) {
        Tender tender = tenderRepository.getTenderById(updatedTender.getId());
        tender.setStatus(updatedTender.getStatus());
        tender.setDescription(updatedTender.getDescription());
        tender.setName(updatedTender.getName());
        tenderRepository.save(tender);
        return createTenderDTO(tender);
    }

    public TenderDTO delete(long id) {
        List<Proposition> propositions = propositionRepository.deletePropositionsByTenderId(id);
        Tender tender = tenderRepository.getReferenceById(id);
        TenderUrlConnector tenderUrlConnector = tender.getTenderUrlConnector();
        tenderUrlRepository.delete(tender.getTenderUrlConnector());
        tenderRepository.delete(tender);

        return new TenderDTO(tender, tenderUrlConnector, propositions);
    }

    private List<TenderDTO> createTenderDTOList(List<Tender> tenderList) {
        List<TenderDTO> tenderDTOList = new LinkedList<>();

        for (Tender tender : tenderList) {
            TenderUrlConnector url = tender.getTenderUrlConnector();
            List<Proposition> propositions = propositionRepository.getPropositionsByTenderId(tender.getId());
            tenderDTOList.add(new TenderDTO(tender, url, propositions));
        }

        return tenderDTOList;
    }


    private TenderDTO createTenderDTO(Tender tender) {
        TenderUrlConnector tenderUrlConnector = tenderUrlRepository.getTenderUrlConnectorByTenderId(tender.getId());
        List<Proposition> propositions = propositionRepository.getPropositionsByTenderId(tender.getId());
        return new TenderDTO(tender, tenderUrlConnector, propositions);
    }

    private TenderUrlConnectorDTO createTenderUrlConnectorDTO(TenderUrlConnector tenderUrlConnector){
        return new TenderUrlConnectorDTO(
                tenderUrlConnector.getTender().getId(),
                tenderUrlConnector.getUrl()
        );
    }
}