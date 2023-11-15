package com.labs.tenderservice.entity.tender;

public class TenderURLConnector  {
    private final long tenderId;
    private String url;

    public TenderURLConnector(long tenderID, String url) {
        this.tenderId = tenderID;
        this.url = url;
    }

    public long getTenderID() {
        return tenderId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
