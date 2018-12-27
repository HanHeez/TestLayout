package com.gtv.hanhee.testlayout.model;

public class Product {
    private String name;
    private String id;
    private long price;
    private long discountPrice;
    private String description;
    private int discountPercent;
    private long quantity;
    private String image;

    public Product(String name, String id, long price, long discountPrice, String description, int discountPercent, long quantity, String image) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.discountPrice = discountPrice;
        this.description = description;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
