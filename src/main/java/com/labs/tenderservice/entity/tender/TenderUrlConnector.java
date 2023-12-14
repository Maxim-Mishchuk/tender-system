package com.labs.tenderservice.entity.tender;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQuery(name = "TenderUrlConnector.getTenderUrlConnectorByTenderId", query = "select t from TenderUrlConnector t where t.tender.id=?1")
@Table(name = "tender_url_connector")
@Getter
@Setter
public class TenderUrlConnector {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tender_id", referencedColumnName = "Id")
    @JsonIgnore
    private Tender tender;
    private String url;

    public TenderUrlConnector() {
    }

    public TenderUrlConnector(long id, Tender tender, String url) {
        this.id = id;
        this.tender = tender;
        this.url = url;
    }

}