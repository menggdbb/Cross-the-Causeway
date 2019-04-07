package com.tehosiewdai.gojbboh.entity;

public class Weather {

    private String area;

    private String description;

    public Weather(String area, String description){
        this.area = area;
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public String getDescription() {
        return description;
    }
}
