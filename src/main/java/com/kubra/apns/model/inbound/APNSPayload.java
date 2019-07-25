package com.kubra.apns.model.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.kubra.apns.model.inbound.APNSNotification;

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