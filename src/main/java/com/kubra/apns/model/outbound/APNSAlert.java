package com.kubra.apns.model.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class APNSAlert {
    private String title;
    private String subtitle;
    private String body;

    @JsonProperty("launch-image")
    @SerializedName("launch-image")
    private String launchImage;

    @JsonProperty("title-loc-key")
    @SerializedName("title-loc-key")
    private String titleLocKey;

    @JsonProperty("title-loc-args")
    @SerializedName("title-loc-args")
    private ArrayList<String> titleLocArgs;

    @JsonProperty("loc-key")
    @SerializedName("loc-key")
    private String locKey;

    @JsonProperty("loc-args")
    @SerializedName("loc-args")
    private ArrayList<String> locArgs;

    public APNSAlert() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLaunchImage() {
        return launchImage;
    }

    public void setLaunchImage(String launchImage) {
        this.launchImage = launchImage;
    }

    public String getTitleLocKey() {
        return titleLocKey;
    }

    public void setTitleLocKey(String titleLocKey) {
        this.titleLocKey = titleLocKey;
    }

    public ArrayList<String> getTitleLocArgs() {
        return titleLocArgs;
    }

    public void setTitleLocArgs(ArrayList<String> titleLocArgs) {
        this.titleLocArgs = titleLocArgs;
    }

    public String getLocKey() {
        return locKey;
    }

    public void setLocKey(String locKey) {
        this.locKey = locKey;
    }

    public ArrayList<String> getLocArgs() {
        return locArgs;
    }

    public void setLocArgs(ArrayList<String> locArgs) {
        this.locArgs = locArgs;
    }
}
