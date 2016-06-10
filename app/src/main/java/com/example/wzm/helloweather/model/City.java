package com.example.wzm.helloweather.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by wzm on 2016/5/25.
 */
public class City {
    private String cityName;
    private String cityCode;
    private String provinceName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

//    public static ArrayList<City> readCityFromDatabase(SQLiteDatabase db, String provinceNameTmp) {
//        ArrayList<City> cityArrayList = new ArrayList<City>();
//        Cursor cursor = db.query("city", null, "province_name = ?", new String[]{provinceNameTmp}, null, null, null);
//        if (cursor.getCount() <= 0) {
//            return null;
//        } else {
//            if (cursor.moveToFirst()) {
//                do {
//                    String cityName = cursor.getString(cursor.getColumnIndex("city_name"));
//                    String cityCode = cursor.getString(cursor.getColumnIndex("city_code"));
//                    String provinceName = cursor.getString(cursor.getColumnIndex("province_name"));
//                    City city = new City();
//                    city.setCityName(cityName);
//                    city.setCityCode(cityCode);
//                    city.setProvinceName(provinceName);
//                    cityArrayList.add(city);
//                } while (cursor.moveToNext());
//            }
//        }
//        cursor.close();
//        return cityArrayList;
//    }
}
