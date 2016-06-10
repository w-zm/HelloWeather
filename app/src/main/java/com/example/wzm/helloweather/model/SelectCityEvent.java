package com.example.wzm.helloweather.model;

/**
 * Created by wzm on 2016/6/10.
 */
public class SelectCityEvent {
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public SelectCityEvent(String cityName) {
        this.cityName = cityName;
    }
}
