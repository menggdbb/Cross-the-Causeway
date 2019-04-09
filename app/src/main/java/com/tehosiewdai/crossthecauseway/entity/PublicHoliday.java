package com.tehosiewdai.crossthecauseway.entity;

/**
 * Represents a public holiday.
 */
public class PublicHoliday {

    /**
     * Date of the public holiday.
     */
    private String date;

    /**
     * Name of the public holiday.
     */
    private String name;

    /**
     * Country of the public holiday.
     */
    private String country;

    /**
     * Instantiates the PublicHoliday object.
     *
     * @param date    date of the public holiday.
     * @param name    name of the public holiday.
     * @param country country of the public holiday.
     */
    public PublicHoliday(String date, String name, String country){
        this.date = date;
        this.name = name;
        this.country = country;
    }

    /**
     * Gets the name of the public holiday.
     * @return name of the public holiday.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the country of the public holiday.
     * @return country of the public holiday.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the date of the public holiday.
     * @return date the of the public holiday.
     */
    public String getDate() {
        return date;
    }
}
