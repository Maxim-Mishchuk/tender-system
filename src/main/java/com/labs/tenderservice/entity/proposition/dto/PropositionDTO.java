package com.labs.tenderservice.entity.proposition.dto;

import com.labs.tenderservice.entity.proposition.Proposition;
import lombok.Data;


@Data
public class PropositionDTO {
    private final long id;
    private final long tenderId;
    private final String name;
    private final String description;
    private final double price;
    private final Proposition.Currency currency;
    private final Proposition.Status status;
}
