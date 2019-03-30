package com.tehosiewdai.gojbboh.entity;

public class TrafficObject {

    private String cameraId;
    private String imageUrl;
    private String datetime;

    public TrafficObject(String cameraId, String imageUrl, String datetime){
        this.cameraId = cameraId;
        this.imageUrl = imageUrl;
        this.datetime = datetime;
    }

    public String getCameraId() {
        return cameraId;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
