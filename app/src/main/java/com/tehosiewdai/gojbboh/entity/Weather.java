package com.tehosiewdai.gojbboh.entity;

/**
 * Represents a weather description.
 */
public class Weather {

    /**
     * Location of the weather update.
     */
    private String area;

    /**
     * Description of the weather.
     */
    private String description;

    /**
     * Instantiates weather description object.
     *
     * @param area        location.
     * @param description description of the weather.
     */
    public Weather(String area, String description) {
        this.area = area;
        this.description = description;
    }

    /**
     * Gets the location.
     *
     * @return the location.
     */
    public String getArea() {
        return area;
    }

    /**
     * Gets the weather description.
     *
     * @return the weather description.
     */
    public String getDescription() {
        return description;
    }
}
