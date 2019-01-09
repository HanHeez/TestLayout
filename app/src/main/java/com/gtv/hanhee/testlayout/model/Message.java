package com.gtv.hanhee.testlayout.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("Message")
    @Expose
    private String Message;

    public String getMessage() {
        return Message;
    }

    public Message(String message) {
        Message = message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}