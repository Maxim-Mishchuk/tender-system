package com.labs.tenderservice.entity.tender;

public class Tender {
    private final long id;
    private final long userId;
    private String name;
    private String description;
    private Status status;

    public Tender(long id, long userId, String name, String description, Status status) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
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
