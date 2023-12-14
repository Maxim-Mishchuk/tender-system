package com.labs.tenderservice.entity.proposition.dto;

import com.labs.tenderservice.entity.proposition.Proposition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Data
public class PropositionDTO {
    @PositiveOrZero
    private final long id;
    @PositiveOrZero
    private final long tenderId;
    @NotBlank
    private final String name;
    private final String description;
    @Positive
    private final double price;
    @NotNull
    private final Proposition.Currency currency;
    @NotNull
    private final Proposition.Status status;

    public static PropositionDTO getDTO(Proposition proposition) {
        return new PropositionDTO(
                proposition.getId(),
                proposition.getTender().getId(),
                proposition.getName(),
                proposition.getDescription(),
                proposition.getPrice(),
                proposition.getCurrency(),
                proposition.getStatus()
        );
    }

    public static List<PropositionDTO> getList(List<Proposition> propositionList) {
        return propositionList.stream()
                .map(PropositionDTO::getDTO)
                .toList();
    }
}
