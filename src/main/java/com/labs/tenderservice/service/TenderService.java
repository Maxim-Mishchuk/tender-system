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
        tender = tenderRepository.save(tender);

        return TenderDTO.getDTO(tender);
    }

    public TenderUrlConnectorDTO updateUrl(TenderUrlConnectorDTO updatedTenderUrlConnector) {
        TenderUrlConnector tenderUrlConnector =
                tenderUrlRepository.getTenderUrlConnectorByTenderId(updatedTenderUrlConnector.getTenderId());
                tenderUrlConnector.setUrl(updatedTenderUrlConnector.getUrl());
        return createTenderUrlConnectorDTO(tenderUrlRepository.save(tenderUrlConnector));
    }

    public List<TenderDTO> getAll() {
        return TenderDTO.getList(tenderRepository.findAll());
    }

    public List<TenderDTO> getActive() {
        return TenderDTO.getList(tenderRepository.getActiveTenders());
    }


    public List<TenderDTO> getByKeywords(String keywords) {
        return TenderDTO.getList(tenderRepository.getTendersByKeywords(keywords));
    }

    public TenderDTO getById(long id) {
        Tender tender = tenderRepository.getReferenceById(id);
        return TenderDTO.getDTO(tender);
    }

    public TenderDTO getTenderByURL(String url) {
        Tender tender = tenderUrlRepository.getTenderByUrl(url);
        return getById(tender.getId());
    }

    public TenderDTO update(TenderDTO updatedTender) {
        Tender tender = tenderRepository.getTenderById(updatedTender.getId());
        tender.setStatus(updatedTender.getStatus());
        tender.setDescription(updatedTender.getDescription());
        tender.setName(updatedTender.getName());
        tender = tenderRepository.save(tender);
        return TenderDTO.getDTO(tender);
    }

    public TenderDTO delete(long id) {
        List<Proposition> propositions = propositionRepository.deletePropositionsByTenderId(id);
        Tender tender = tenderRepository.getReferenceById(id);
        TenderUrlConnector tenderUrlConnector = tender.getTenderUrlConnector();
        tenderUrlRepository.delete(tender.getTenderUrlConnector());
        tenderRepository.delete(tender);

        return TenderDTO.getDTO(tender);
    }

    private TenderUrlConnectorDTO createTenderUrlConnectorDTO(TenderUrlConnector tenderUrlConnector){
        return new TenderUrlConnectorDTO(
                tenderUrlConnector.getTender().getId(),
                tenderUrlConnector.getUrl()
        );
    }
}