package com.kubra.apns.model.outbound;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class APNSClientResponse {

    private final ArrayList<APNSResponse> failures;

    public APNSClientResponse(ArrayList<APNSResponse> failures) {
        this.failures = failures;
    }

    public ArrayList<APNSResponse> getFailures() {
        return failures;
    }
}
