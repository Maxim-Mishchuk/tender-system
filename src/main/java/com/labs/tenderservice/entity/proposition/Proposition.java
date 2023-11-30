package com.labs.tenderservice.entity.proposition;

public class Proposition{
    private final long id;
    private final long tenderId;
    private String name;
    private String description;
    private Double price;
    private Currency currency;
    private Status status;

    public Proposition(long id, long tenderId, String name, String description, Double price, Currency currency, Status status) {
        this.id = id;
        this.tenderId = tenderId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public long getTenderId() {
        return tenderId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Currency{
        USD, EUR, UAH
    }

    public enum Status {
        ACTIVE, APPROVED, DISMISSED
    }
}
