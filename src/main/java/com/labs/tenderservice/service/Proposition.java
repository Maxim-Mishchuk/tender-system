package com.labs.tenderservice.service;

public class Proposition {
    private final long id;
    private String description;
    private final long tenderId;
    private Double price;
    private final String name;
    private Status status;
    private Currency currency;

    public Proposition(long id, String description, long tenderId, Double price, String name, Status status, Currency currency) {
        this.id = id;
        this.description = description;
        this.tenderId = tenderId;
        this.price = price;
        this.name = name;
        this.status = status;
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTenderId() {
        return tenderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
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
