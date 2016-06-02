package com.example.wzm.helloweather.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wzm on 2016/5/29.
 */
public class Weather implements Serializable {

    public BasicEntity basic;

    public String status;

    public AqiEntity aqi;

    public List<AlarmsEntity> alarms;

    public NowEntity now;

    @SerializedName("daily_forecast") public List<DailyForecastEntity> dailyForcast;

    @SerializedName("hourly_forecast") public List<HourlyForecastEntity> hourlyForecast;

    public SuggestionEntity suggestion;

    public static class BasicEntity implements Serializable {
        public String city;
        public String cnty;
        public String id;
        public String lat;
        public String lon;
        public UpdateEntity update;

        public static class UpdateEntity implements Serializable {
            public String loc;
            public String utc;
        }
    }


    public static class AqiEntity implements Serializable {
        public CityEntity city;

        public static class CityEntity implements Serializable {
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

    public static class AlarmsEntity implements Serializable {
        public String level;
        public String stat;
        public String title;
        public String txt;
        public String type;
    }


    public static class NowEntity implements Serializable {
        public CondEntity cond;
        public String f1;
        public String hum;
        public String pcpn;
        public String pres;
        public String tmp;
        public String vis;
        public WindEntity wind;

        public static class CondEntity implements Serializable {
            public String code;
            public String txt;
        }

        public static class WindEntity implements Serializable {
            public String deg;
            public String dir;
            public String sc;
            public String spd;
        }
    }

    public static class DailyForecastEntity implements Serializable {
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

        public static class AstroEntity implements Serializable {
            public String sr;
            public String ss;
        }

        public static class CondEntity implements Serializable {
            public String code_d;
            public String code_n;
            public String txt_d;
            public String txt_n;
        }

        public static class TmpEntity implements Serializable {
            public String max;
            public String min;
        }

        public static class WindEntity implements Serializable {
            public String deg;
            public String dir;
            public String sc;
            public String spd;
        }
    }

    public static class HourlyForecastEntity implements Serializable {
        public String date;
        public String hum;
        public String pop;
        public String pres;
        public String tmp;
        public WindEntity wind;

        public static class WindEntity implements Serializable {
            public String deg;
            public String dir;
            public String sc;
            public String spd;
        }
    }

    public static class SuggestionEntity implements Serializable {
        public ComfEntity comf;
        public CwEntity cw;
        public DrsgEntity drsg;
        public FluEntity flu;
        public TravEntity trav;
        public UvEntity uv;

        public static class ComfEntity implements Serializable {
            public String brf;
            public String txt;
        }

        public static class CwEntity implements Serializable {
            public String brf;
            public String txt;
        }

        public static class DrsgEntity implements Serializable {
            public String brf;
            public String txt;
        }

        public static class FluEntity implements Serializable {
            public String brf;
            public String txt;
        }

        public static class TravEntity implements Serializable {
            public String brf;
            public String txt;
        }

        public static class UvEntity implements Serializable {
            public String brf;
            public String txt;
        }
    }
}
