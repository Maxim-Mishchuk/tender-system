package com.labs.tenderservice.service;

public class Tender {
    private final long id;
    private final long userId;
    private String description;
    private final String name;
    private Status status;

    public Tender(long id, long userId, String description, String name, Status status) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.name = name;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public enum Status {
        ACTIVE, APPROVED, DISMISSED
    }
}
