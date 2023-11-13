package com.labs.tenderservice.entity.tender;
public class TenderUrlConnector {
    private final long tenderId;
    private final String url;
    public TenderUrlConnector(long tenderID, String url) {
        this.tenderId = tenderID;
        this.url = url;
    }
    public long getTenderId() {
        return tenderId;
    }

    public String getUrl() {
        return url;
    }
}