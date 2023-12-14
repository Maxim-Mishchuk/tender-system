package com.labs.tenderservice.entity.proposition.dto;

import com.labs.tenderservice.entity.proposition.Proposition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PropositionCreateDTO {
    @PositiveOrZero
    private final long tenderId;
    @NotBlank
    private final String name;
    private final String description;
    @PositiveOrZero
    private final double price;
    @NotNull
    private final Proposition.Currency currency;
    @NotNull
    private Proposition.Status status;
}
