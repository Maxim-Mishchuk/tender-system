package com.labs.tenderservice.entity;

public abstract class IDEntity {
    protected final ID id;

    public IDEntity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }
}
