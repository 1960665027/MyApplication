package com.example.administrator.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2018/3/26.
 */

public class SplashActivity extends AppCompatActivity{

    private LinearLayout ll_splash;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        ll_splash = this.findViewById(R.id.ll_splash);
        AlphaAnimation aa = new AlphaAnimation(0.0f,1.0f);
        aa.setDuration(1000);
        ll_splash.setAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent mainActivity = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainActivity);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
