package com.example.wzm.helloweather.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by wzm on 2016/5/25.
 */
public class Province {
    private String provinceName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

//    public static ArrayList<Province> readProvinceFromDatabase(SQLiteDatabase db) {
//        ArrayList<Province> provinceArrayList = new ArrayList<Province>();
//        Cursor cursor = db.query("province", null, null, null, null, null, null);
//        if (cursor.getCount() <= 0) {
//            return null;
//        } else {
//            if (cursor.moveToFirst()) {
//                do {
//                    String provinceName = cursor.getString(cursor.getColumnIndex("province_name"));
//                    Province province = new Province();
//                    province.setProvinceName(provinceName);
//                    provinceArrayList.add(province);
//                } while (cursor.moveToNext());
//            }
//        }
//        cursor.close();
//        return provinceArrayList;
//    }

}
