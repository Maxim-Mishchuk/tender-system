package com.labs.tenderservice.entity.tender.dto;

import com.labs.tenderservice.entity.tender.Tender;
import lombok.Data;

import java.io.Serializable;

@Data
public class TenderCreateDTO implements Serializable {
    private final long userId;
    private final String name;
    private final String description;
    private Tender.Status status;
}
