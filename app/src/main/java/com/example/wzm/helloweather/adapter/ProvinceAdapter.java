package com.example.wzm.helloweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wzm.helloweather.R;
import com.example.wzm.helloweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2016/6/9.
 */
public class ProvinceAdapter extends ArrayAdapter<Province> {
    private Context mContext;
    private List<Province> mProvinceList;

    public ProvinceAdapter(Context context, ArrayList<Province> provinceArrayList) {
        super(context, 0, provinceArrayList);
        mContext = context;
        mProvinceList = provinceArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Province province = mProvinceList.get(position);

        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_province, parent, false);

            viewHolder.provinceName = (TextView) convertView.findViewById(R.id.province_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.provinceName.setText(province.getProvinceName());

        return convertView;
    }

    private static class ViewHolder {
        public TextView provinceName;
    }
}