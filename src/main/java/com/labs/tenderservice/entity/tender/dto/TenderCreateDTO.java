package com.labs.tenderservice.entity.tender.dto;

import com.labs.tenderservice.entity.tender.Tender;
import lombok.Data;

@Data
public class TenderCreateDTO {
    private final long userId;
    private final String name;
    private final String description;
    private Tender.Status status;
}
