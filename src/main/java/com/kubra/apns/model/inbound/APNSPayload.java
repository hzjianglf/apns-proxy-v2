package com.kubra.apns.model.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class APNSPayload {

    @JsonProperty("aps")
    @SerializedName("aps")
    private APNSNotification notification;

    @JsonProperty("additionalInfo")
    private Object additionalInfo;

    public APNSPayload() { }

    public APNSNotification getNotification() {
        return notification;
    }

    public void setNotification(APNSNotification notification) {
        this.notification = notification;
    }

    public Object getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Object additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}