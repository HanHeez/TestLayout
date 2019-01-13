package com.gtv.hanhee.testlayout.model;

import android.media.Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeekDiary implements Serializable {

    @SerializedName("week")
    @Expose
    private Integer week;
    @SerializedName("images")
    @Expose
    private List<Diary> diaries = null;

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public List<Diary> getDiaries() {
        return diaries;
    }

    public void setDiaries(List<Diary> diaries) {
        this.diaries = diaries;
    }
}
