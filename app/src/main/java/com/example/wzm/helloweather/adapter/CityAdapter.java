package com.example.wzm.helloweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wzm.helloweather.R;
import com.example.wzm.helloweather.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2016/6/9.
 */
public class CityAdapter extends ArrayAdapter<City> {
    private Context mContext;
    private List<City> mCityList;

    public CityAdapter(Context context, ArrayList<City> cityArrayList) {
        super(context, 0, cityArrayList);
        mContext = context;
        mCityList = cityArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City city = mCityList.get(position);

        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_city_list, parent, false);

            viewHolder.cityName = (TextView) convertView.findViewById(R.id.city_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.cityName.setText(city.getCityName());
        return convertView;
    }

    private static class ViewHolder {
        public TextView cityName;
    }
}
