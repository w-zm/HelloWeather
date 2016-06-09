package com.example.wzm.helloweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2016/6/8.
 */
public class CityAPI {
    @SerializedName("city_info") @Expose
    public List<CityInfo> cityAPIList = new ArrayList<>();
}
