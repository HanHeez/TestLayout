package com.gtv.hanhee.testlayout.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "products")
public class Product implements Serializable {

    @PrimaryKey
    @NonNull
    @SerializedName("_id")
    @Expose
    private String id;
    private String name;
    private long price;
    @Ignore
    private List<SubCategory> subCategories = null;

    @ColumnInfo(name="discount_percent")
    private int discountPercent;
    @ColumnInfo(name="short_description")
    private String shortDescription;
    private String description;
    @ColumnInfo(name="discount_price")
    private long discountPrice;
    private long quantity;
    @ColumnInfo(name="order_amount")
    private int orderAmount;
    @ColumnInfo(name="order_total_price")
    private long orderTotalPrice;
    @ColumnInfo(name="order_shop_total_product")
    private int orderShopTotalProduct;
    private String avatar;
    @ColumnInfo(name="is_free_ship")
    private boolean isFreeShip;
    @ColumnInfo(name="is_checked_product")
    private boolean isCheckedProduct = true;
    @Embedded
    private Shop shop;
    @Ignore
    List<String> thumbnails;

    public int getOrderShopTotalProduct() {
        return orderShopTotalProduct;
    }

    public void setOrderShopTotalProduct(int orderShopTotalProduct) {
        this.orderShopTotalProduct = orderShopTotalProduct;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public long getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(long orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

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

    public Product() {
    }

    @Ignore
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
    @Ignore
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


    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public boolean isCheckedProduct() {
        return isCheckedProduct;
    }

    public void setCheckedProduct(boolean checkedProduct) {
        isCheckedProduct = checkedProduct;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
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
