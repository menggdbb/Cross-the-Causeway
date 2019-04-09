package com.tehosiewdai.gojbboh.entity;

import com.google.android.gms.maps.model.LatLng;

/**
 * Represents a Money Changer in Singapore.
 */
public class MoneyChanger {

    /**
     * Coordinates of the Money Changer.
     */
    private LatLng latLng;

    /**
     * Name of the Money Changer.
     */
    private String name;

    /**
     * Address of the Money Changer.
     */
    private String address;

    /**
     * Postal Code of the Money Changer.
     */
    private String postalCode;

    /**
     * Instantiates the MoneyChanger object.
     *
     * @param lat        latitude of the coordinates for the location.
     * @param lng        longitude of the coordinates for the location.
     * @param name       name of the Money Changer.
     * @param address    address of the Money Changer.
     * @param postalCode postal code of the Money Changer.
     */
    public MoneyChanger(double lat, double lng, String name, String address, String postalCode) {
        this.latLng = new LatLng(lat, lng);
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
    }

    /**
     * Gets the address of the Money Changer.
     *
     * @return address of the Money Changer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets te name of the Money Changer.
     *
     * @return name of the Money Changer.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the postal code of the Money Changer.
     *
     * @return postal code of the money Changer.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets the postal code of the Money Changer.
     *
     * @return postal code of the Money Changer.
     */
    public LatLng getLatLng() {
        return latLng;
    }
}
