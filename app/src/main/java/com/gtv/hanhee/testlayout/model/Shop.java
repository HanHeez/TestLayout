package com.gtv.hanhee.testlayout.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;


public class Shop implements Serializable {

    @ColumnInfo(name="shop_id")
    private String id;
    @ColumnInfo(name="shop_name")
    private String name;
    @ColumnInfo(name="shop_avatar")
    private String avatar;
    @ColumnInfo(name="shop_description")
    private String description;
    @ColumnInfo(name="shop_shipper")
    private String shipper;


    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public Shop() {
    }

    @Ignore
    public Shop(String id, String name, String avatar, String description, String shipper) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.description = description;
        this.shipper = shipper;
    }

    @Ignore
    public Shop(String id, String name, String avatar, String description) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
