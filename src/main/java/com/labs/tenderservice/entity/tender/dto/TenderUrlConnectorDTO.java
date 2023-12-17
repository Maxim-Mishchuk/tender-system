package com.labs.tenderservice.entity.tender.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class TenderUrlConnectorDTO {
    @PositiveOrZero
    private long tenderId;

    @Pattern(regexp = "\\w+]")
    private String url;

    public TenderUrlConnectorDTO(long tenderId, String url) {
        this.tenderId = tenderId;
        this.url = url;
    }
}
