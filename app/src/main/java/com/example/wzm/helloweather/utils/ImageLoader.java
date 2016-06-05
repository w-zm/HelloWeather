package com.example.wzm.helloweather.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by wzm on 2016/6/4.
 */
public class ImageLoader {

    public static void load(Context context, @DrawableRes String imgUrl, ImageView view) {
        Glide.with(context).load(imgUrl).crossFade().into(view);
    }

    public static void clear(Context context) {
        Glide.get(context).clearMemory();
    }
}
