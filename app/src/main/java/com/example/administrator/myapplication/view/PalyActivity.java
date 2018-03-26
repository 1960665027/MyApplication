package com.example.administrator.myapplication.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2018/3/24.
 */

public class PalyActivity extends AppCompatActivity {
    private VideoView mVideo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paly_layout);
        mVideo = this.findViewById(R.id.id_video);
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String name = bundle.getString("_ID").replace("\r\n", "");
        Log.i("TAG", name);
        mVideo.setMediaController(new MediaController(this));
        mVideo.setVideoURI(Uri.parse(name));
        mVideo.start();
        mVideo.requestFocus();
    }
}
