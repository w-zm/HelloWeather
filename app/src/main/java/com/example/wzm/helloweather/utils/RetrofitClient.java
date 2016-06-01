package com.example.wzm.helloweather.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.wzm.helloweather.Contants;
import com.example.wzm.helloweather.Interface.IWeather;
import com.example.wzm.helloweather.model.Weather;
import com.example.wzm.helloweather.model.WeatherAPI;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wzm on 2016/5/31.
 */
public class RetrofitClient {
    private static IWeather iWeather = null;
    private static Retrofit retrofit = null;
    private Handler mHandler;

    private static class SingletonHolder {
        private static final RetrofitClient INSTANCE = new RetrofitClient();
    }

    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(Handler handler) {
        mHandler = handler;

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.heweather.com/x3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        iWeather = retrofit.create(IWeather.class);
    }

    public void getWeather(String cityName) {
        Call<WeatherAPI> call = iWeather.getWeatherAPI(cityName, Contants.KEY);

        call.enqueue(new Callback<WeatherAPI>() {
            @Override
            public void onResponse(Call<WeatherAPI> call, Response<WeatherAPI> response) {
                if (response != null) {
                    Weather weather = new Weather();
                    weather = response.body().mHeWeatherDataService30s.get(0);
                    Log.e("test3", weather.status);
                    Message msg = new Message();
                    msg.what = Contants.NETWORK_CALLBACK;
                    msg.obj = weather;
                    mHandler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call<WeatherAPI> call, Throwable t) {
                Log.e("test", "请求错误");
            }
        });
    }
}
