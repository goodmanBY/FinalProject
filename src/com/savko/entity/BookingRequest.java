package com.savko.entity;

import java.util.Date;

public class BookingRequest {

    private int requestId;
    private int userId;
    private int amountOfPlaces;
    private Date dateFrom;
    private Date dateTo;
    private double cost;
    private byte confirmed;
    private byte declined;
    private byte paid;
    private String approvedBy;

    public int getRequestId() {
        return requestId;
    }

    public BookingRequest setRequestId(int requestId) {
        this.requestId = requestId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public BookingRequest setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getAmountOfPlaces() {
        return amountOfPlaces;
    }

    public BookingRequest setAmountOfPlaces(int amountOfPlaces) {
        this.amountOfPlaces = amountOfPlaces;
        return this;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public BookingRequest setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public BookingRequest setDateTo(Date dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public BookingRequest setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public byte getConfirmed() {
        return confirmed;
    }

    public BookingRequest setConfirmed(byte confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public byte getDeclined() {
        return declined;
    }

    public BookingRequest setDeclined(byte declined) {
        this.declined = declined;
        return this;
    }

    public byte getPaid() {
        return paid;
    }

    public BookingRequest setPaid(byte paid) {
        this.paid = paid;
        return this;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public BookingRequest setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
        return this;
    }
}
