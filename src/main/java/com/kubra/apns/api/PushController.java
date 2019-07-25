package com.kubra.apns.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kubra.apns.internal.APNSTokenGenerator;
import com.kubra.apns.internal.PushService;
import com.kubra.apns.model.inbound.APNRequest;
import com.kubra.apns.model.outbound.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("apn")
public class PushController {

    @Autowired
    private PushService pushService;

    @PostMapping("notification")
    public APNSClientResponse sendNotification(@RequestBody APNRequest request) throws Exception {
        if (request.getPayload() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "payload must not be empty");
        }
        else if (request.getPrivateKey() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "privateKey must not be empty");
        }

        APNSTokenGenerator generator = new APNSTokenGenerator(request.getKeyId(), request.getTeamId(), request.getPrivateKey());
        String signedAuthToken = generator.getSignedToken();

        Gson GSON = new GsonBuilder().create();
        String payloadJson = GSON.toJson(request.getPayload());

        System.out.println(payloadJson);

        return pushService.sendPushNotifications(request.getDeviceTokens(), signedAuthToken, payloadJson, request.getBundleId());
    }
}
