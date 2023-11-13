package com.labs.tenderservice.entity.tender;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.IDEntity;

public class Tender extends IDEntity {
    private final ID userId;
    private String name;
    private String description;
    private Status status;

    public Tender(ID id, ID userId, String name, String description, Status status) {
        super(id);
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public ID getId() {
        return id;
    }

    public ID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        ACTIVE, FROZEN, CLOSED
    }
}
