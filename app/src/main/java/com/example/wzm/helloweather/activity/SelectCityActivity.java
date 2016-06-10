package com.example.wzm.helloweather.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wzm.helloweather.R;
import com.example.wzm.helloweather.adapter.CityAdapter;
import com.example.wzm.helloweather.db.DBManager;
import com.example.wzm.helloweather.model.City;
import com.example.wzm.helloweather.model.Province;
import com.example.wzm.helloweather.model.SelectCityEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2016/6/9.
 */
public class SelectCityActivity extends AppCompatActivity {
    private ListView lvCityName;
    private CityAdapter mCityAdapter;
    private ArrayList<City> cityArrayList;
    private DBManager mDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        String provinceName = getIntent().getStringExtra("provinceName");

        mDBManager = DBManager.getInstance(this);
        cityArrayList = mDBManager.readCityFromDatabase(provinceName);
        mCityAdapter = new CityAdapter(this, cityArrayList);
        lvCityName = (ListView) findViewById(R.id.lv_city_list);
        if (lvCityName != null) {
            lvCityName.setAdapter(mCityAdapter);
        }

        lvCityName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City city = cityArrayList.get(position);
                EventBus.getDefault().post(new SelectCityEvent(city.getCityName()));
                finish();
            }
        });
    }
}
