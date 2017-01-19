package com.savko.entity;

import java.util.Date;

public class PaymentInfo {

    private int paymentInfoId;
    private int userId;
    private int requestId;
    private int lastFourDigits;
    private double cost;
    private Date dateAndTime;

    public int getPaymentInfoId() {
        return paymentInfoId;
    }

    public PaymentInfo setPaymentInfoId(int paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public PaymentInfo setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getRequestId() {
        return requestId;
    }

    public PaymentInfo setRequestId(int requestId) {
        this.requestId = requestId;
        return this;
    }

    public int getLastFourDigits() {
        return lastFourDigits;
    }

    public PaymentInfo setLastFourDigits(int lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public PaymentInfo setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public PaymentInfo setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
        return this;
    }
}
