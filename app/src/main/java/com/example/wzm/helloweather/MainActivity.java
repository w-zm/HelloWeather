package com.example.wzm.helloweather;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.example.wzm.helloweather.activity.AboutActivity;
import com.example.wzm.helloweather.activity.SelectProvinceActivity;
import com.example.wzm.helloweather.activity.SettingActivity;
import com.example.wzm.helloweather.adapter.MyAdapter;
import com.example.wzm.helloweather.model.SelectCityEvent;
import com.example.wzm.helloweather.model.Weather;
import com.example.wzm.helloweather.utils.ACache;
import com.example.wzm.helloweather.utils.RetrofitClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private MyAdapter myAdapter;
    private ACache mACache;
    private Weather mWeather = new Weather();
    private PullRefreshLayout pullRefreshLayout;
    private TextView toolbarTitle;
    private RecyclerView recyclerView;
    private String curName = "广州";                   //暂且默认城市为广州

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Contants.NETWORK_CALLBACK:
                    Weather weather = new Weather();
                    weather = (Weather) msg.obj;
                    //mWeather = weather;
                    mWeather.status = weather.status;
                    mWeather.aqi = weather.aqi;
                    mWeather.basic = weather.basic;
                    mWeather.suggestion = weather.suggestion;
                    mWeather.now = weather.now;
                    mWeather.dailyForcast = weather.dailyForcast;
                    mWeather.hourlyForecast = weather.hourlyForecast;
                    //Log.e("test2", mWeather.suggestion.cw.txt);
                    if (pullRefreshLayout != null) {
                        pullRefreshLayout.setRefreshing(false);
                    }
                    myAdapter.notifyDataSetChanged();
                    mACache.put(mWeather.basic.city, mWeather, Contants.CACHE_TIME);       //暂定缓存时间为1个小时
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        mACache = ACache.get(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
//            toolbar.setTitle("ddddaaaaaaaaaa");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        //mWeather = (Weather) mACache.getAsObject("广州");
        //Log.e("haha2", mWeather.status);
//        mWeather.suggestion.cw.txt = "aaa";
        myAdapter = new MyAdapter(this, mWeather);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(myAdapter);
        }


        pullRefreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        if (pullRefreshLayout != null) {
            pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    netWork();
                }
            });
        }

//        if (pullRefreshLayout != null) {
//            pullRefreshLayout.setRefreshing(false);
//        }

        //netWork();   //一开始先获取weather
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }



    private void netWork() {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        retrofitClient.init(handler);
        retrofitClient.getWeather(curName);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        item.setChecked(true);
//                        setTitle(item.getTitle());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.select_city:
                        Intent intentSelectCity = new Intent(MainActivity.this, SelectProvinceActivity.class);
                        startActivity(intentSelectCity);
                        item.setChecked(true);
//                        setTitle(item.getTitle());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.setting:
                        Intent intentSetting = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intentSetting);
                        item.setChecked(true);
//                        setTitle(item.getTitle());
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.about:
                        Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intentAbout);
                        item.setChecked(true);
//                        setTitle(item.getTitle());
                        drawerLayout.closeDrawers();
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


//    private void showPopupMenu(View view) {
//        // View当前PopupMenu显示的相对View的位置
//        PopupMenu popupMenu = new PopupMenu(this, view);
//        // menu布局
//        popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
//        // menu的item点击事件
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
//        // PopupMenu关闭事件
//        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
//            @Override
//            public void onDismiss(PopupMenu menu) {
//                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        popupMenu.show();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSelectCityEvent(SelectCityEvent event) {
        String cityName = event.getCityName();
        toolbarTitle.setText(cityName);
        Snackbar.make(recyclerView, "当前城市为：" + cityName, Snackbar.LENGTH_SHORT).show();
        this.curName = cityName;
        netWork();
    }
}
