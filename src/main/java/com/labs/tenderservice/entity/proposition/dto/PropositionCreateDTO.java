package com.labs.tenderservice.entity.proposition.dto;

import com.labs.tenderservice.entity.proposition.Proposition;
import lombok.Data;

import java.io.Serializable;

@Data
public class PropositionCreateDTO implements Serializable {
    private final long tenderId;
    private final String name;
    private final String description;
    private final double price;
    private final Proposition.Currency currency;
    private Proposition.Status status;
}
