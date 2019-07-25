package com.kubra.apns.model.inbound;

public class APNRequest {
    private final String keyId;
    private final String teamId;
    private final String[] deviceTokens;
    private final String bundleId;
    private final String privateKey;
    private final APNSPayload payload;

    public APNRequest(String keyId, String teamId, String[] deviceTokens, String bundleId, String privateKey, APNSPayload payload) {
        this.keyId = keyId;
        this.teamId = teamId;
        this.deviceTokens = deviceTokens;
        this.bundleId = bundleId;
        this.privateKey = privateKey;
        this.payload = payload;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String[] getDeviceTokens() {
        return deviceTokens;
    }

    public String getBundleId() {
        return bundleId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public APNSPayload getPayload() {
        return payload;
    }
}
