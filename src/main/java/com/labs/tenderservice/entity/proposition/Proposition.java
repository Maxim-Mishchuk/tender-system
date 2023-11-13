package com.labs.tenderservice.entity.proposition;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.IDEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proposition extends IDEntity {
    private final ID tenderId;
    private String name;
    private String description;
    private Double price;
    private Currency currency;
    private Status status;

    public Proposition(ID id, ID tenderId, String name, String description, Double price, Currency currency, Status status) {
        super(id);
        this.tenderId = tenderId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.status = status;
    }

    public enum Currency{
        USD, EUR, UAH
    }

    public enum Status {
        ACTIVE, APPROVED, DISMISSED
    }
}
