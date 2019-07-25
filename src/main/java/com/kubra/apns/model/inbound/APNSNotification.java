package com.kubra.apns.model.inbound;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class APNSNotification {

    @JsonProperty("alert")
    private APNSAlert alert;

    @JsonProperty("badge")
    private Integer badge;

    @JsonProperty("sound")
    private String sound;

    @JsonProperty("thread-id")
    @SerializedName("thread-id")
    private String threadId;

    @JsonProperty("category")
    private String category;

    @JsonProperty("content-available")
    @SerializedName("content-available")
    private Integer contentAvailable;

    @JsonProperty("mutable-content")
    @SerializedName("mutable-content")
    private Integer mutableContent;

    public APNSNotification() { }

    public APNSAlert getAlert() {
        return alert;
    }

    public void setAlert(APNSAlert alert) {
        this.alert = alert;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getContentAvailable() {
        return contentAvailable;
    }

    public void setContentAvailable(int contentAvailable) {
        this.contentAvailable = contentAvailable;
    }

    public int getMutableContent() {
        return mutableContent;
    }

    public void setMutableContent(int mutableContent) {
        this.mutableContent = mutableContent;
    }
}
