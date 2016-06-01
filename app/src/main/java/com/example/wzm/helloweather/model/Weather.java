package com.example.wzm.helloweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wzm on 2016/5/29.
 */
public class Weather {

    public BasicEntity basic;

    public String status;

    public AqiEntity aqi;

    public List<AlarmsEntity> alarms;

    public NowEntity now;

    @SerializedName("daily_forecast") public List<DailyForecastEntity> dailyForcast;

    @SerializedName("hourly_forecast") public List<HourlyForecastEntity> hourlyForecast;

    public SuggestionEntity suggestion;

    public static class BasicEntity {
        public String city;
        public String cnty;
        public String id;
        public String lat;
        public String lon;
        public UpdateEntity update;

        public static class UpdateEntity {
            public String loc;
            public String utc;
        }
    }


    public static class AqiEntity {
        public CityEntity city;

        public static class CityEntity {
            public String aqi;
            public String co;
            public String no2;
            public String o3;
            public String pm10;
            public String pm25;
            public String qlty;
            public String so2;
        }
    }

    public static class AlarmsEntity {
        public String level;
        public String stat;
        public String title;
        public String txt;
        public String type;
    }


    public static class NowEntity {
        public CondEntity cond;
        public String f1;
        public String hum;
        public String pcpn;
        public String pres;
        public String tmp;
        public String vis;
        public WindEntity wind;

        public static class CondEntity {
            public String code;
            public String txt;
        }

        public static class WindEntity {
            public String deg;
            public String dir;
            public String sc;
            public String spd;
        }
    }

    public static class DailyForecastEntity {
        public String date;
        public AstroEntity astro;
        public CondEntity cond;
        public String hum;
        public String pnpc;
        public String pop;
        public String pres;
        public TmpEntity tmp;
        public String vis;
        public WindEntity wind;

        public static class AstroEntity {
            public String sr;
            public String ss;
        }

        public static class CondEntity {
            public String code_d;
            public String code_n;
            public String txt_d;
            public String txt_n;
        }

        public static class TmpEntity {
            public String max;
            public String min;
        }

        public static class WindEntity {
            public String deg;
            public String dir;
            public String sc;
            public String spd;
        }
    }

    public static class HourlyForecastEntity {
        public String date;
        public String hum;
        public String pop;
        public String pres;
        public String tmp;
        public WindEntity wind;

        public static class WindEntity {
            public String deg;
            public String dir;
            public String sc;
            public String spd;
        }
    }

    public static class SuggestionEntity {
        public ComfEntity comf;
        public CwEntity cw;
        public DrsgEntity drsg;
        public FluEntity flu;
        public TravEntity trav;
        public UvEntity uv;

        public static class ComfEntity {
            public String brf;
            public String txt;
        }

        public static class CwEntity {
            public String brf;
            public String txt;
        }

        public static class DrsgEntity {
            public String brf;
            public String txt;
        }

        public static class FluEntity {
            public String brf;
            public String txt;
        }

        public static class TravEntity {
            public String brf;
            public String txt;
        }

        public static class UvEntity {
            public String brf;
            public String txt;
        }
    }
}
