package com.savko.entity;

import java.util.Date;

public class Request {

    private int userId;
    private int amountOfPlaces;
    private Date dateFrom;
    private Date dateTo;
    private double cost;

    public Request() {

    }

    public Request(int userId, int amountOfPlaces, Date dateFrom, Date dateTo, double cost) {
        this.userId = userId;
        this.amountOfPlaces = amountOfPlaces;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.cost = cost;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAmountOfPlaces() {
        return amountOfPlaces;
    }

    public void setAmountOfPlaces(int placesNumber) {
        this.amountOfPlaces = placesNumber;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
