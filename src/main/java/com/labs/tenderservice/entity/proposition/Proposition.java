package com.labs.tenderservice.entity.proposition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labs.tenderservice.entity.tender.Tender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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

    public Proposition() {

    }

    public enum Currency{
        USD, EUR, UAH
    }

    public enum Status {
        NEW, ACTIVE, APPROVED, DISMISSED
    }

    public Proposition(Tender tender, String name, String description, Double price, Currency currency, Status status) {
        this.id = System.nanoTime();
        this.tender = tender;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.status = status;
    }
}
