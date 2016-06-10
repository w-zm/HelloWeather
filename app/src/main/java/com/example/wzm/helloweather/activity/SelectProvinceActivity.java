package com.example.wzm.helloweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wzm.helloweather.R;
import com.example.wzm.helloweather.adapter.ProvinceAdapter;
import com.example.wzm.helloweather.db.DBManager;
import com.example.wzm.helloweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2016/6/7.
 */
public class SelectProvinceActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lvProvinceList;
    private ProvinceAdapter mProvinceAdapter;
    private ArrayList<Province> mProvinceList;
    private DBManager mDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province_list);

        mDBManager = DBManager.getInstance(this);
        lvProvinceList = (ListView) findViewById(R.id.lv_province_list);
        mProvinceList = mDBManager.readProvinceFromDatabase();
        mProvinceAdapter = new ProvinceAdapter(this, mProvinceList);
        lvProvinceList.setAdapter(mProvinceAdapter);

        lvProvinceList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Province province = mProvinceList.get(position);

        Intent intent = new Intent(SelectProvinceActivity.this, SelectCityActivity.class);
        intent.putExtra("provinceName", province.getProvinceName());
        startActivity(intent);
        finish();
    }
}
