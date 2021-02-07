package com.tohandesign.turkeycovidtracker;

public class VaccineItem {

    private String cityName;
    private int value;

    public VaccineItem(String cityName, int value) {
        this.cityName = cityName;
        this.value = value;
    }
    public VaccineItem() {
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
