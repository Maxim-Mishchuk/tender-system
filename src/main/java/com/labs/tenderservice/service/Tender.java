package com.labs.tenderservice.service;

public class Tender {
    private final int id;
    private final int userId;
    private String description;
    private final String name;
    private Status status;

    public Tender(int id, int userId, String description, String name, Status status) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.name = name;
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
