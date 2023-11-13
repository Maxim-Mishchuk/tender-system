package com.labs.tenderservice.entity.tender;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.IDEntity;

public class TenderUrlConnector extends IDEntity {
    private String url;

    public TenderUrlConnector(ID tenderId, String url) {
        super(tenderId);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
