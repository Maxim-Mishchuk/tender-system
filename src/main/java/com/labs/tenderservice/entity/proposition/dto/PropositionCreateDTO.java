package com.labs.tenderservice.entity.proposition.dto;

import com.labs.tenderservice.entity.proposition.Proposition;
import lombok.Data;

@Data
public class PropositionCreateDTO {
    private final long tenderId;
    private final long name;
    private final long description;
    private final double price;
    private final Proposition.Currency currency;
    private Proposition.Status status;
}
