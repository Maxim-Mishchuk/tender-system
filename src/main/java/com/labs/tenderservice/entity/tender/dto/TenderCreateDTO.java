package com.labs.tenderservice.entity.tender.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class TenderCreateDTO {
    @PositiveOrZero
    private final long userId;
    @NotBlank
    private final String name;
    private final String description;
}
