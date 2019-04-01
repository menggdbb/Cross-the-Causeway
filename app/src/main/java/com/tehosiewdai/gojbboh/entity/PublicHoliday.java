package com.tehosiewdai.gojbboh.entity;

public class PublicHoliday {

    private String date;
    private String name;
    private String country;

    public PublicHoliday(String date, String name, String country){
        this.date = date;
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }
}
