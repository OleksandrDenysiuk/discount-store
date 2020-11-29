package com.example.discount_store.model;

public class Discount {
    private String productName;
    private String name;
    private double rate;
    private String start;
    private String end;

    public Discount() {
    }

    public Discount(String productName, String name, double rate, String start, String end) {
        this.productName = productName;
        this.name = name;
        this.rate = rate;
        this.start = start;
        this.end = end;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
