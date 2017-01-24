package com.savko.entity;

public class Discount {

    private int discountId;
    private int discount;

    public int getDiscountId() {
        return discountId;
    }

    public Discount setDiscountId(int discountId) {
        this.discountId = discountId;
        return this;
    }

    public int getDiscount() {
        return discount;
    }

    public Discount setDiscount(int discount) {
        this.discount = discount;
        return this;
    }
}
