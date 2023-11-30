package com.labs.tenderservice.entity.proposition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labs.tenderservice.entity.tender.Tender;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class Proposition{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "TenderID")
    @JsonIgnore
    private Tender tender;
    private String name;
    private String description;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Currency{
        USD, EUR, UAH
    }

    public enum Status {
        ACTIVE, APPROVED, DISMISSED
    }
}
