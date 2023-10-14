package com.labs.tenderservice.entity.tender;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.IDEntity;

public class TenderURLConnector extends IDEntity {
    private String URL;

    public TenderURLConnector(ID tenderID, String URL) {
        super(tenderID);
        this.URL = URL;
    }

    public ID getTenderID() {
        return getId();
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

}
