package com.tehosiewdai.gojbboh.entity;

public class TrafficImage {

    private String cameraId;
    private String imageUrl;
    private String datetime;

    public TrafficImage(String cameraId, String imageUrl, String datetime){
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
