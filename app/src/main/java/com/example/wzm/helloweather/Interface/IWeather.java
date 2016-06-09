package com.example.wzm.helloweather.Interface;

import com.example.wzm.helloweather.model.CityAPI;
import com.example.wzm.helloweather.model.WeatherAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wzm on 2016/6/1.
 */
public interface IWeather {
    @GET("weather")
    Call<WeatherAPI> getWeatherAPI(@Query("city") String cityName, @Query("key") String key);

    @GET("citylist")
    Call<CityAPI> getCityAPI(@Query("search") String search, @Query("key") String key);
}
