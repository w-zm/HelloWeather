package com.example.wzm.helloweather.adapter;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.sax.RootElement;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wzm.helloweather.R;
import com.example.wzm.helloweather.model.Weather;
import com.example.wzm.helloweather.utils.ImageLoader;

import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by wzm on 2016/5/29.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Weather mWeather;
    private Context mContext;
    private static final int FIRST_TYPE = 0;
    private static final int SECOND_TYPE = 1;
    private static final int THIRD_TYPE = 2;
    private static final int FOURTH_TYPE = 3;

    public MyAdapter(Context context, Weather weather) {
        mContext = context;
        this.mWeather = weather;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case FIRST_TYPE:
                return FIRST_TYPE;
            case SECOND_TYPE:
                return SECOND_TYPE;
            case THIRD_TYPE:
                return THIRD_TYPE;
            case FOURTH_TYPE:
                return FOURTH_TYPE;
        }

        return FIRST_TYPE;                               //error
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case FIRST_TYPE:
                View view1 = inflater.inflate(R.layout.item_temperature, parent, false);
                viewHolder = new ViewHolder1(view1);
                break;
            case SECOND_TYPE:
                View view2 = inflater.inflate(R.layout.item_future, parent, false);
                viewHolder = new ViewHolder2(view2);
                break;
            case THIRD_TYPE:
                View view3 = inflater.inflate(R.layout.item_hourly_wrapper, parent, false);
                viewHolder = new ViewHolder3(view3);
                break;
            case FOURTH_TYPE:
                View view4 = inflater.inflate(R.layout.item_suggestion, parent, false);
                viewHolder = new ViewHolder4(view4);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case FIRST_TYPE:
                ViewHolder1 vh1 = (ViewHolder1) holder;
                configureViewHolder1(vh1, position);
                break;
            case SECOND_TYPE:
                ViewHolder2 vh2 = (ViewHolder2) holder;
                configureViewHolder2(vh2, position);
                break;
            case THIRD_TYPE:
                ViewHolder3 vh3 = (ViewHolder3) holder;
                configureViewHolder3(vh3, position);
                break;
            case FOURTH_TYPE:
                ViewHolder4 vh4 = (ViewHolder4) holder;
                configureViewHolder4(vh4, position);
                break;
        }
    }

    private void configureViewHolder4(ViewHolder4 vh4, int position) {
        vh4.dressSuggestion.setText(String.format("%s", mWeather.suggestion.drsg.brf));
        vh4.dressContent.setText(String.format("%s", mWeather.suggestion.drsg.txt));
        vh4.travelSuggestion.setText(String.format("%s", mWeather.suggestion.trav.brf));
        vh4.travelContent.setText(String.format("%s", mWeather.suggestion.trav.txt));
        vh4.sportsSuggestion.setText(String.format("%s", mWeather.suggestion.sport.brf));
        vh4.sportsContent.setText(String.format("%s", mWeather.suggestion.sport.txt));
        vh4.washcarSuggestion.setText(String.format("%s", mWeather.suggestion.cw.brf));
        vh4.washcarContent.setText(String.format("%s", mWeather.suggestion.cw.txt));
    }

    private void configureViewHolder3(ViewHolder3 vh3, int position) {
        int length = mWeather.hourlyForecast.size();

        for (int i = 0; i < length; i++) {
            String mDate = mWeather.hourlyForecast.get(i).date;
            vh3.time[i].setText(mDate.substring(mDate.length() - 5, mDate.length()));
            vh3.temperature[i].setText(String.format("%s℃", mWeather.hourlyForecast.get(i).tmp));   //这是参考SeeWeather的方法
            vh3.hum[i].setText(String.format("%s%%", mWeather.hourlyForecast.get(i).hum));
        }
    }

    private void configureViewHolder2(ViewHolder2 vh2, int position) {
        String urlToday = "http://files.heweather.com/cond_icon/" + mWeather.now.cond.code + ".png";
        ImageLoader.load(mContext, urlToday, vh2.todayCond);
        String todayCond = mWeather.dailyForcast.get(0).cond.txt_d
                + "转" + mWeather.dailyForcast.get(0).cond.txt_n
                + " | " + mWeather.dailyForcast.get(0).wind.dir;
        vh2.todayCondQlty.setText(todayCond);
        String todayTemperature = mWeather.dailyForcast.get(0).tmp.max + "℃"
                + " / " + mWeather.dailyForcast.get(0).tmp.min + "℃";
        vh2.todayTemperature.setText(todayTemperature);

        String urlTomorrow = "http://files.heweather.com/cond_icon/" + mWeather.dailyForcast.get(1).cond.code_d + ".png";
        ImageLoader.load(mContext, urlTomorrow, vh2.tomorrowCond);
        String tomorrowCond = mWeather.dailyForcast.get(1).cond.txt_d
                + "转" + mWeather.dailyForcast.get(1).cond.txt_n
                + " | " + mWeather.dailyForcast.get(1).wind.dir;
        vh2.tomorrowCondQlty.setText(tomorrowCond);
        String tomorrowTemperature = mWeather.dailyForcast.get(1).tmp.max + "℃"
                + " / " + mWeather.dailyForcast.get(1).tmp.min + "℃";
        vh2.tomorrowTemperature.setText(tomorrowTemperature);

        String urlAfterTomorrow = "http://files.heweather.com/cond_icon/" + mWeather.dailyForcast.get(2).cond.code_d + ".png";
        ImageLoader.load(mContext, urlAfterTomorrow, vh2.afterTomorrowCond);
        String afterTomorrowCond = mWeather.dailyForcast.get(2).cond.txt_d
                + "转" + mWeather.dailyForcast.get(2).cond.txt_n
                + " | " + mWeather.dailyForcast.get(2).wind.dir;
        vh2.afterTomorrowCondQlty.setText(afterTomorrowCond);
        String afterTomorrowTemperature = mWeather.dailyForcast.get(2).tmp.max + "℃"
                + " / " + mWeather.dailyForcast.get(2).tmp.min + "℃";
        vh2.afterTomorrowTemperature.setText(afterTomorrowTemperature);
    }

    private void configureViewHolder1(ViewHolder1 vh1, int position) {
//        int condCode = Integer.valueOf(mWeather.now.cond.code);
//        if (200 <= condCode && condCode <= 213) {
//            vh1.cond.setImageResource(R.drawable.wind_gif);
//        }
//        if (300 <= condCode && condCode <= 313) {
//            vh1.cond.setImageResource(R.drawable.rain_gif);
//        }

        vh1.temperature.setText(mWeather.now.tmp);
        String s = mWeather.basic.city + " | " + mWeather.now.cond.txt;
        vh1.cityNameWeather.setText(s);
        vh1.wind.setText(mWeather.now.wind.dir);
        vh1.sc.setText(mWeather.now.wind.sc);
        vh1.hum.setText(mWeather.now.hum);
        vh1.qlty.setText(mWeather.aqi.city.qlty);
    }

    @Override
    public int getItemCount() {
        return Objects.equals(mWeather.status, "ok") ? 4 : 0;
    }



    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        private GifImageView cond;
        private TextView temperature;
        private TextView cityNameWeather;
        private TextView wind;
        private TextView sc;
        private TextView qlty;
        private TextView hum;

        public ViewHolder1(View itemView) {
            super(itemView);

            cond = (GifImageView) itemView.findViewById(R.id.cond);
            temperature = (TextView) itemView.findViewById(R.id.temperature);
            cityNameWeather = (TextView) itemView.findViewById(R.id.city_name_weather);
            wind = (TextView) itemView.findViewById(R.id.wind);
            sc = (TextView) itemView.findViewById(R.id.sc);
            qlty = (TextView) itemView.findViewById(R.id.qlty);
            hum = (TextView) itemView.findViewById(R.id.hum);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private ImageView todayCond;
        private TextView todayCondQlty;
        private TextView todayTemperature;
        private ImageView tomorrowCond;
        private TextView tomorrowCondQlty;
        private TextView tomorrowTemperature;
        private ImageView afterTomorrowCond;
        private TextView afterTomorrowCondQlty;
        private TextView afterTomorrowTemperature;

        public ViewHolder2(View itemView) {
            super(itemView);

            todayCond = (ImageView) itemView.findViewById(R.id.today_cond);
            todayCondQlty = (TextView) itemView.findViewById(R.id.today_cond_qlty);
            todayTemperature = (TextView) itemView.findViewById(R.id.today_temperature);

            tomorrowCond = (ImageView) itemView.findViewById(R.id.tomorrow_cond);
            tomorrowCondQlty = (TextView) itemView.findViewById(R.id.tomorrow_cond_qlty);
            tomorrowTemperature = (TextView) itemView.findViewById(R.id.tomorrow_temperature);

            afterTomorrowCond = (ImageView) itemView.findViewById(R.id.after_tomorrow_cond);
            afterTomorrowCondQlty = (TextView) itemView.findViewById(R.id.after_tomorrow_cond_qlty);
            afterTomorrowTemperature = (TextView) itemView.findViewById(R.id.after_tomorrow_temperature);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        private LinearLayout itemHourInfoLinearLayout;
        private TextView[] time = new TextView[mWeather.hourlyForecast.size()];
        private TextView[] temperature = new TextView[mWeather.hourlyForecast.size()];
        private TextView[] hum = new TextView[mWeather.hourlyForecast.size()];

        public ViewHolder3(View itemView) {
            super(itemView);
            itemHourInfoLinearLayout = (LinearLayout) itemView.findViewById(R.id.item_hourly_linearlayout);

            int length = mWeather.hourlyForecast.size();

            for (int i = 0; i < length; i++) {
                View view = View.inflate(mContext, R.layout.item_hourly, null);
                time[i] = (TextView) view.findViewById(R.id.time);
                temperature[i] = (TextView) view.findViewById(R.id.temperature);
                hum[i] = (TextView) view.findViewById(R.id.hum);
                itemHourInfoLinearLayout.addView(view);
            }
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {
        private TextView dressSuggestion;
        private TextView dressContent;
        private TextView travelSuggestion;
        private TextView travelContent;
        private TextView sportsSuggestion;
        private TextView sportsContent;
        private TextView washcarSuggestion;
        private TextView washcarContent;

        public ViewHolder4(View itemView) {
            super(itemView);

            dressSuggestion = (TextView) itemView.findViewById(R.id.dress_suggestion);
            dressContent = (TextView) itemView.findViewById(R.id.dress_content);
            travelSuggestion = (TextView) itemView.findViewById(R.id.travel_suggestion);
            travelContent = (TextView) itemView.findViewById(R.id.travel_content);
            sportsSuggestion = (TextView) itemView.findViewById(R.id.sports_suggestion);
            sportsContent = (TextView) itemView.findViewById(R.id.sports_content);
            washcarSuggestion = (TextView) itemView.findViewById(R.id.washcar_suggestion);
            washcarContent = (TextView) itemView.findViewById(R.id.washcar_content);
        }
    }
}
