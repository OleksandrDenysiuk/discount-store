package com.example.discount_store.model;

public class Product {
    String name;
    int amount;
    String shop;
    String type;

    public Product() {
    }

    public Product(String name, int amount, String shop, String type) {
        this.name = name;
        this.amount = amount;
        this.shop = shop;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
