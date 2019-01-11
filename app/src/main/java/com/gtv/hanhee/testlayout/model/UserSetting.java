package com.gtv.hanhee.testlayout.model;

import android.support.annotation.DrawableRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSetting {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("services")
    @Expose
    private Integer services;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getServices() {
        return services;
    }

    public void setServices(Integer services) {
        this.services = services;
    }

    public UserSetting(String name, @DrawableRes int drawableImage) {
        this.name = name;
        this.drawableImage = drawableImage;
    }
    private @DrawableRes int drawableImage;

    public int getDrawableImage() {
        return drawableImage;
    }

    public void setDrawableImage(int drawableImage) {
        this.drawableImage = drawableImage;
    }
}
