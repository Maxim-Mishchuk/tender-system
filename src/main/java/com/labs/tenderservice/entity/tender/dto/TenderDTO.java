package com.labs.tenderservice.entity.tender.dto;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Getter
public class TenderDTO implements Serializable {
    private  long id;
    private  long userId;
    private  String url;
    private  String name;
    private  String description;
    private  Tender.Status status;
    private  List<Proposition> propositionList;

    public TenderDTO(Tender tender, TenderUrlConnector tenderUrlConnector, List<Proposition> propositionList) {
        this.id = tender.getId();
        this.userId = tender.getUser().getId();
        this.url = tenderUrlConnector.getUrl();
        this.name = tender.getName();
        this.description = tender.getDescription();
        this.status = tender.getStatus();
        this.propositionList = propositionList;
    }

    public TenderDTO(Tender tender, TenderUrlConnector tenderUrlConnector) {
        this.id = tender.getId();
        this.userId = tender.getUser().getId();
        this.url = tenderUrlConnector.getUrl();
        this.name = tender.getName();
        this.description = tender.getDescription();
        this.status = tender.getStatus();
        this.propositionList = Collections.emptyList();
    }

    public static List<TenderDTO> getList(List<Tender> tenderList, List<TenderUrlConnector> tenderUrlConnectorList) {
        return tenderUrlConnectorList.stream()
                .filter(urlConnector -> tenderList.stream().anyMatch(tender -> tender.getId() == urlConnector.getTender().getId()))
                .map(urlConnector -> {
                   Tender tender = tenderList.stream().filter(t -> t.getId() == urlConnector.getTender().getId()).findFirst().get();
                   return new TenderDTO(tender, urlConnector);
                })
                .toList();
    }

    public TenderDTO() {
    }
}
