package com.kubra.apns.internal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kubra.apns.model.outbound.APNSClientResponse;
import com.kubra.apns.model.outbound.APNSResponse;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import org.conscrypt.Conscrypt;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.ArrayList;

@Service
public class PushService {

    private final MediaType JSON = MediaType.parse("application/json");
    private final Gson GSON = new GsonBuilder().serializeNulls().create();

    public APNSClientResponse sendPushNotifications(String[] deviceTokens, String signedAuthToken, String payload, String bundleId) throws Exception {
        Security.insertProviderAt(Conscrypt.newProvider(),1);

        ArrayList<APNSResponse> responses = new ArrayList<>();

        for (String deviceToken: deviceTokens) {
            APNSResponse response = sendNotification(deviceToken, signedAuthToken, payload, bundleId);
            if (!response.isEmpty()) { responses.add(response); }
        }

        return new APNSClientResponse(responses);
    }

    private APNSResponse sendNotification(String deviceToken, String signedAuthToken, String payload, String bundleId) throws Exception {
        String url = "https://api.sandbox.push.apple.com:443/3/device/" + deviceToken;

        OkHttpClient client = new OkHttpClient.Builder().build();

        RequestBody body = RequestBody.create(JSON, payload);
        Request request = new Request.Builder()
                .headers(getRequestHeaders(signedAuthToken, bundleId))
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.getBuffer();
        String responseBody = buffer.clone().readString(StandardCharsets.UTF_8);

        if (responseBody.isEmpty()) {
            return new APNSResponse();
        }
        else {
            APNSResponse apnsResponse = GSON.fromJson(responseBody, APNSResponse.class);
            apnsResponse.setDeviceToken(deviceToken);
            return apnsResponse;
        }
    }

    private Headers getRequestHeaders(String signedAuthToken, String bundleId) {

        return new okhttp3.Headers.Builder()
                .add("Content-Type", "application/json")
                .add("Authorization", signedAuthToken)
                .add("apns-priority", "10")
                .add("apns-expiration", "0")
                .add("apns-topic", bundleId)
                .add("apns-push-type", "alert")
                .build();
    }

    // May be useful in the future
//    private Exception handleErrorResponse(APNSResponse response, int responseCode) {
//        switch (responseCode) {
//
//            case 400:
//                return new ResponseStatusException(HttpStatus.BAD_REQUEST, response.getReason());
//
//            case 403:
//                return new ResponseStatusException(HttpStatus.FORBIDDEN, response.getReason());
//
//            case 404:
//                return new ResponseStatusException(HttpStatus.NOT_FOUND, response.getReason());
//
//            case 405:
//                return new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, response.getReason());
//
//            case 410:
//                return new ResponseStatusException(HttpStatus.GONE, response.getReason());
//
//            case 413:
//                return new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, response.getReason());
//
//            case 429:
//                return new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, response.getReason());
//
//            case 500:
//                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, response.getReason());
//
//            case 503:
//                return new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, response.getReason());
//
//            default:
//                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unknown error occurred");
//        }
//    }
}
