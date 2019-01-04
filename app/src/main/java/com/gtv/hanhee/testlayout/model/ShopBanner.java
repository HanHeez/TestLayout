package com.gtv.hanhee.testlayout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopBanner {

    @SerializedName("_id")
    @Expose
    private String id;
    private String imageUrl;
    private String targetType;
    private String targetId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
