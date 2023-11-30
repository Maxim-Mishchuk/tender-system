package com.labs.tenderservice.entity.dto;

import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.TenderUrlConnector;
import com.labs.tenderservice.entity.user.User;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Getter
public class TenderDTO {
    private final long id;
    private final User user;
    private final String url;
    private final String name;
    private final String description;
    private final Tender.Status status;
    private final List<Proposition> propositionList;

    public TenderDTO(Tender tender, TenderUrlConnector tenderUrlConnector, List<Proposition> propositionList) {
        this.id = tender.getId();
        this.user = tender.getUser();
        this.url = tenderUrlConnector.getUrl();
        this.name = tender.getName();
        this.description = tender.getDescription();
        this.status = tender.getStatus();
        this.propositionList = propositionList;
    }

    public TenderDTO(Tender tender, TenderUrlConnector tenderUrlConnector) {
        this.id = tender.getId();
        this.user = tender.getUser();
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
}
