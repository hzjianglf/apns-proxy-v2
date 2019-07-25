package com.kubra.apns.model.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class APNSPayload {

    @JsonProperty("aps")
    @SerializedName("aps")
    private APNSNotification notification;

    public APNSPayload() { }

    public APNSNotification getNotification() {
        return notification;
    }

    public void setNotification(APNSNotification notification) {
        this.notification = notification;
    }
}