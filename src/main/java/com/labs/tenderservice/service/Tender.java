package com.labs.tenderservice.service;

public class Tender {
    private final int id;
    private final int userId;
    private Status status;

    public Tender(int id, int userId, Status status) {
        this.id = id;
        this.userId = userId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        ACTIVE, APPROVED, DISMISSED
    }
}
