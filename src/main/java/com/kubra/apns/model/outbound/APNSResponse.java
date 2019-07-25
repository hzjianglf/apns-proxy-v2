package com.kubra.apns.model.outbound;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class APNSResponse {

    private String reason;
    private String deviceToken;

    public APNSResponse() {}

    public APNSResponse(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    @JsonIgnore
    public boolean isEmpty() {
        if (reason != null) {
            return reason.isEmpty();
        } else {
            return true;
        }
    }
}
