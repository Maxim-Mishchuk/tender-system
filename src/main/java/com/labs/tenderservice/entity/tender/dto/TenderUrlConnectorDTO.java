package com.labs.tenderservice.entity.tender.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TenderUrlConnectorDTO implements Serializable {
    private long tenderId;
    private String url;

    public TenderUrlConnectorDTO(long tenderId, String url) {
        this.tenderId = tenderId;
        this.url = url;
    }
}
