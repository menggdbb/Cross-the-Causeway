package com.tehosiewdai.gojbboh.entity;

/**
 * Represents an traffic image.
 */
public class TrafficImage {

    /**
     * Camera ID for the traffic image.
     */
    private String cameraId;

    /**
     * URL string for the traffic image.
     */
    private String imageUrl;

    /**
     * Datetime of the traffic image retrieved.
     */
    private String datetime;

    /**
     * Instantiates the traffic image object.
     *
     * @param cameraId camera ID.
     * @param imageUrl URL string for the traffic image.
     * @param datetime datetime of the traffic image retrieved.
     */
    public TrafficImage(String cameraId, String imageUrl, String datetime) {
        this.cameraId = cameraId;
        this.imageUrl = imageUrl;
        this.datetime = datetime;
    }

    /**
     * Gets the Camera ID for the traffic image.
     *
     * @return Camera ID for the traffic image.
     */
    public String getCameraId() {
        return cameraId;
    }

    /**
     * Gets the datetime of the traffic image retrieved.
     *
     * @return datetime of the traffic image retrieved.
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * GEts the URL string for the traffic image.
     *
     * @return URL string for the traffic image.
     */
    public String getImageUrl() {
        return imageUrl;
    }
}
