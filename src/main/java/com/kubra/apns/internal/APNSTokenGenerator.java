package com.kubra.apns.internal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kubra.apns.model.outbound.APNSClaims;
import com.kubra.apns.model.outbound.APNSHeader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import io.netty.handler.codec.base64.Base64Dialect;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;

public class APNSTokenGenerator {

    final private APNSHeader header;
    final private APNSClaims claims;
    final private String privateKey;

    final private Charset ASCII = StandardCharsets.US_ASCII;

    public APNSTokenGenerator(String keyID, String teamId, String privateKey) {
        this.header = new APNSHeader(keyID);
        this.claims = new APNSClaims(teamId, Instant.now().getEpochSecond());
        this.privateKey = privateKey;
    }

    public String getSignedToken() throws Exception {
        Gson GSON = new GsonBuilder().create();

        String headerJSON = GSON.toJson(header);
        String claimsJSON = GSON.toJson(claims);

        String formattedPrivateKey = createFormattedKey();

        StringBuilder payloadBuilder = new StringBuilder();
        payloadBuilder.append(encodedBase64String(headerJSON.getBytes(ASCII)));
        payloadBuilder.append('.');
        payloadBuilder.append(encodedBase64String(claimsJSON.getBytes(ASCII)));

        byte[] privateKeyBytes = decodeBase64EncodedString(formattedPrivateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");

        ECPrivateKey signedPrivateKey = (ECPrivateKey) keyFactory.generatePrivate(keySpec);

        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(signedPrivateKey);
        signature.update(payloadBuilder.toString().getBytes(ASCII));

        byte[] signedBytes = signature.sign();

        payloadBuilder.append('.');
        payloadBuilder.append(encodedBase64String(signedBytes));

        return "bearer " + payloadBuilder.toString();
    }

    private String encodedBase64String(byte[] data) {
        ByteBuf wrappedString = Unpooled.wrappedBuffer(data);
        ByteBuf encodedString = Base64.encode(wrappedString, Base64Dialect.URL_SAFE);

        String formattedString = encodedString.toString(ASCII).replace("=","");

        wrappedString.release();
        encodedString.release();

        return formattedString;
    }

    private byte[] decodeBase64EncodedString(final String base64EncodedString) {
        ByteBuf base64EncodedByteBuf = Unpooled.wrappedBuffer(base64EncodedString.getBytes(ASCII));

        ByteBuf decodedByteBuf = Base64.decode(base64EncodedByteBuf);
        byte[] decodedBytes = new byte[decodedByteBuf.readableBytes()];

        decodedByteBuf.readBytes(decodedBytes);
        base64EncodedByteBuf.release();
        decodedByteBuf.release();

        return decodedBytes;
    }

    private String createFormattedKey() throws IOException {
        StringBuilder privateKeyBuilder = new StringBuilder();
        InputStream inputStream = new ByteArrayInputStream(privateKey.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        for (String line; (line = reader.readLine()) != null;) {
            if (line.contains("BEGIN PRIVATE KEY")) {
                continue;
            }
            else if (line.contains("END PRIVATE KEY")) {
                break;
            }
            else {
                privateKeyBuilder.append(line);
            }
        }

        return privateKeyBuilder.toString();
    }
}