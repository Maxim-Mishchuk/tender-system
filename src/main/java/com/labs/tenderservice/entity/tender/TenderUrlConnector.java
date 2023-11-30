package com.labs.tenderservice.entity.tender;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "tender_url_connector")
@Getter
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