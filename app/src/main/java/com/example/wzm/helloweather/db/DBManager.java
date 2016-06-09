package com.example.wzm.helloweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.wzm.helloweather.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferOverflowException;

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

    private SQLiteDatabase database;
    private Context mContext;

    public DBManager(Context context) {
        this.mContext = context;
    }

    public void openDatabase() {
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
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
        } catch (FileNotFoundException e) {
            Log.e("Database", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public void closeDatabase() {
        this.database.close();
    }
}
