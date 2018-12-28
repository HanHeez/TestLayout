package com.gtv.hanhee.testlayout.model;

import java.util.List;

public class Product {
    private String name;
    private String id;
    private long price;
    private long discountPrice;
    private String description;
    private String shortDescription;
    private int discountPercent;
    private long quantity;
    private String avatar;
    private boolean isFreeShip;
    private List<String> thumbnails;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isFreeShip() {
        return isFreeShip;
    }

    public void setFreeShip(boolean freeShip) {
        isFreeShip = freeShip;
    }

    public List<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Product(String name, String id, long price, long discountPrice, String shortDescription, int discountPercent, long quantity, String avatar) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.discountPrice = discountPrice;
        this.shortDescription = shortDescription;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.avatar = avatar;
    }

    public Product(String name, String id, long price, long discountPrice, String description, String shortDescription, int discountPercent, long quantity, String avatar, boolean isFreeShip, List<String> thumbnails) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.discountPrice = discountPrice;
        this.description = description;
        this.shortDescription = shortDescription;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.avatar = avatar;
        this.isFreeShip = isFreeShip;
        this.thumbnails = thumbnails;
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
}
