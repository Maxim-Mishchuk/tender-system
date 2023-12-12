package com.labs.tenderservice.entity.tender;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labs.tenderservice.entity.proposition.Proposition;
import com.labs.tenderservice.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
public class Tender {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName = "Id")
    @JsonIgnore
    private User user;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "tender")
    List<Proposition>propositionList;
    @OneToOne(mappedBy = "tender")
    TenderUrlConnector tenderUrlConnector;



    public enum Status {
        NEW, ACTIVE, FROZEN, CLOSED
    }
}
