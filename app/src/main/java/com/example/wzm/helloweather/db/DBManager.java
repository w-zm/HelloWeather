package com.example.wzm.helloweather.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.wzm.helloweather.R;
import com.example.wzm.helloweather.model.City;
import com.example.wzm.helloweather.model.Province;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;
import java.util.ArrayList;

/**
 * Created by wzm on 2016/6/9.
 */
public class DBManager {
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "city.db";
    public static final String PACKAGE_NAME = "com.example.wzm.helloweather";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;

    private SQLiteDatabase mDatabase;
    private Context mContext;
    private static DBManager dbManager;

//    public DBManager(Context context) {
//        this.mContext = context;
//    }

    public void openDatabase() {
        this.mDatabase = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    private DBManager(Context context) {
        this.mContext = context;
        this.mDatabase = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    public synchronized static DBManager getInstance(Context context) {
        if (dbManager == null) {
            return new DBManager(context);
        }
        return dbManager;
    }

    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }

    private SQLiteDatabase openDatabase(String dbFile) {
        try {
            if (!(new File(dbFile).exists())) {
                InputStream is = mContext.getResources().openRawResource(R.raw.weather);
                byte[] buffer = new byte[BUFFER_SIZE];
                FileOutputStream fos = new FileOutputStream(dbFile);
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
            return db;
        } catch (IOException e) {
            Log.e("Database", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public void closeDatabase() {
        this.mDatabase.close();
    }

    public ArrayList<City> readCityFromDatabase(String provinceNameTmp) {
        ArrayList<City> cityArrayList = new ArrayList<City>();
        Cursor cursor = mDatabase.query("city", null, "province_name = ?", new String[]{provinceNameTmp}, null, null, null);
        if (cursor.getCount() <= 0) {
            return null;
        } else {
            if (cursor.moveToFirst()) {
                do {
                    String cityName = cursor.getString(cursor.getColumnIndex("city_name"));
                    String cityCode = cursor.getString(cursor.getColumnIndex("city_code"));
                    String provinceName = cursor.getString(cursor.getColumnIndex("province_name"));
                    City city = new City();
                    city.setCityName(cityName);
                    city.setCityCode(cityCode);
                    city.setProvinceName(provinceName);
                    cityArrayList.add(city);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return cityArrayList;
    }

    public ArrayList<Province> readProvinceFromDatabase() {
        ArrayList<Province> provinceArrayList = new ArrayList<Province>();
        Cursor cursor = mDatabase.query("province", null, null, null, null, null, null);
        if (cursor.getCount() <= 0) {
            return null;
        } else {
            if (cursor.moveToFirst()) {
                do {
                    String provinceName = cursor.getString(cursor.getColumnIndex("province_name"));
                    Province province = new Province();
                    province.setProvinceName(provinceName);
                    provinceArrayList.add(province);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return provinceArrayList;
    }
}
