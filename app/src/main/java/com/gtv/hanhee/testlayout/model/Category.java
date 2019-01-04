package com.gtv.hanhee.testlayout.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Category implements Serializable {

    @SerializedName("_id")
    @Expose
    @ColumnInfo(name="category_id")
    private String id;
    @ColumnInfo(name="category_name")
    private String name;
    @ColumnInfo(name="category_description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Ignore
    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category() {
    }
}
