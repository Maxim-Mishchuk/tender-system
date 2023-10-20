package com.labs.tenderservice.entity.tender;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.IDEntity;

public class TenderURLConnector extends IDEntity {
    private String url;

    public TenderURLConnector(ID tenderID, String url) {
        super(tenderID);
        this.url = url;
    }

    public ID getTenderID() {
        return getId();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
