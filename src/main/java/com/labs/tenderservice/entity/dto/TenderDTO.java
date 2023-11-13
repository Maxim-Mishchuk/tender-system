package com.labs.tenderservice.entity.dto;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class TenderDTO {
    private final long id;
    private final long userId;
    private final String url;
    private final String name;
    private final String description;
    private final Tender.Status status;
    private final List<Proposition> propositionList;

    public TenderDTO(Tender tender, TenderUrlConnector tenderUrlConnector, List<Proposition> propositionList) {
        this.id = tender.getId();
        this.userId = tender.getUserId();
        this.url = tenderUrlConnector.getUrl();
        this.name = tender.getName();
        this.description = tender.getDescription();
        this.status = tender.getStatus();
        this.propositionList = propositionList;
    }

    public TenderDTO(Tender tender, TenderUrlConnector tenderUrlConnector) {
        this.id = tender.getId();
        this.userId = tender.getUserId();
        this.url = tenderUrlConnector.getUrl();
        this.name = tender.getName();
        this.description = tender.getDescription();
        this.status = tender.getStatus();
        this.propositionList = Collections.emptyList();
    }

    public static List<TenderDTO> getList(List<Tender> tenderList, List<TenderUrlConnector> tenderUrlConnectorList) {
        return tenderUrlConnectorList.stream()
                .filter(urlConnector -> tenderList.stream().anyMatch(tender -> tender.getId() == urlConnector.getTenderId()))
                .map(urlConnector -> {
                   Tender tender = tenderList.stream().filter(t -> t.getId() == urlConnector.getTenderId()).findFirst().get();
                   return new TenderDTO(tender, urlConnector);
                })
                .toList();
    }
}
