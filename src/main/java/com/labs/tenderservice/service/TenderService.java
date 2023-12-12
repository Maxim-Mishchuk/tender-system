package com.labs.tenderservice.service;

import com.labs.tenderservice.entity.tender.dto.TenderCreateDTO;
import com.labs.tenderservice.entity.tender.dto.TenderDTO;
import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import com.labs.tenderservice.repository.PropositionRepository;
import com.labs.tenderservice.repository.TenderRepository;
import com.labs.tenderservice.repository.TenderUrlRepository;
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

    @Autowired
    public TenderService(TenderRepository tenderRepository, TenderUrlRepository tenderUrlRepository, PropositionRepository propositionRepository) {
        this.tenderRepository = tenderRepository;
        this.tenderUrlRepository = tenderUrlRepository;
        this.propositionRepository = propositionRepository;
    }

    public TenderDTO create(TenderCreateDTO newTender) {
        newTender.setStatus(Tender.Status.NEW);
        Tender tender = tenderRepository.save(newTender);
        TenderUrlConnector newTenderUrlConnector = new TenderUrlConnector(
                System.nanoTime(),
                tender,
                String.valueOf(tender.getId())
        );

        TenderUrlConnector tenderUrlConnector = tenderUrlRepository.save(newTenderUrlConnector);
        System.out.println(tenderUrlConnector);
        return new TenderDTO(tender, tenderUrlConnector);
    }

    public TenderUrlConnector updateUrl(TenderUrlConnector updatedTenderUrlConnector) {
        return tenderUrlRepository.save(updatedTenderUrlConnector);
    }

    public List<TenderDTO> getAll() {
        List<Tender>tenders= tenderRepository.findAll();
        for(Tender tender: tenders){
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
        Tender tender = tenderRepository.save(updatedTender);
        return createTenderDTO(tender);
    }

    public TenderDTO delete(long id) {
        List<Proposition>  propositions = propositionRepository.deletePropositionsByTenderId(id);
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
        TenderUrlConnector tenderUrlConnector = tenderUrlRepository.getReferenceById(tender.getId());
        List<Proposition> propositions = propositionRepository.getPropositionsByTenderId(tender.getId());
        return new TenderDTO(tender, tenderUrlConnector, propositions);
    }
}