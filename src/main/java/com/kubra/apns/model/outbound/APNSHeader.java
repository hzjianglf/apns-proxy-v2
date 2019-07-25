package com.kubra.apns.model.outbound;

import com.google.gson.annotations.SerializedName;

public class APNSHeader {

    @SerializedName("alg")
    private final String algorithm = "ES256";

    @SerializedName("kid")
    private final String keyId;

    public APNSHeader(String keyId) {
        this.keyId = keyId;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getKeyId() {
        return keyId;
    }
}
