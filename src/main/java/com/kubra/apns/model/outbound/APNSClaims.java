package com.kubra.apns.model.outbound;

import com.google.gson.annotations.SerializedName;

public class APNSClaims {

    @SerializedName("iss")
    private final String teamId;

    @SerializedName("iat")
    private final double issuedDate;

    public APNSClaims(String teamId, double issuedDate) {
        this.teamId = teamId;
        this.issuedDate = issuedDate;
    }

    public String getTeamId() {
        return teamId;
    }

    public double getIssuedDate() {
        return issuedDate;
    }
}
