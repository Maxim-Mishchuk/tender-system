package com.labs.tenderservice.entity.tender;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Tender {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", referencedColumnName = "Id")
    @JsonIgnore
    private User user;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "tender", cascade = CascadeType.ALL)
    List<Proposition>propositionList;
    @OneToOne(mappedBy = "tender", cascade = CascadeType.ALL)
    TenderUrlConnector tenderUrlConnector;

    public Tender() {

    }


    public enum Status {
        NEW, ACTIVE, FROZEN, CLOSED
    }

    public Tender(User user, String name, String description, Status status,  TenderUrlConnector tenderUrlConnector) {
        this.id = System.nanoTime();
        this.user = user;
        this.name = name;
        this.description = description;
        this.status = status;
        this.tenderUrlConnector = tenderUrlConnector;
    }
    public Tender(User user, String name, String description, Status status) {
        this.id = System.nanoTime();
        this.user = user;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public void setTenderUrlConnector(TenderUrlConnector tenderUrlConnector) {
        tenderUrlConnector.setTender(this);
        tenderUrlConnector.setUrl(String.valueOf(this.getId()));

        this.tenderUrlConnector = tenderUrlConnector;
    }
}
