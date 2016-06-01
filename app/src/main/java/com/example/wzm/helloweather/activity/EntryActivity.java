package com.example.wzm.helloweather.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wzm.helloweather.MainActivity;
import com.example.wzm.helloweather.R;

public class EntryActivity extends AppCompatActivity {
    private ImageView iv;
    private static final int ANIMATION_DURATION = 2000;
    private static final float SCALE_END = 1.13F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        iv = (ImageView) findViewById(R.id.iv_entry);

        animateImage();
    }

    private void animateImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(iv, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(iv, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                MainActivity.start(EntryActivity.this);
                finish();
            }
        });
    }
}
