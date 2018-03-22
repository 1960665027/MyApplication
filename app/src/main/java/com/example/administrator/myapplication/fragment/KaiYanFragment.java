package com.example.administrator.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adatper.HomeAdatper;
import com.example.administrator.myapplication.bean.Video;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class KaiYanFragment extends Fragment {
    private List<Video> mVideo;
    private Gson mGson = new Gson();
    private HomeAdatper mAdatper;
    private RecyclerView mRecylerView;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String)msg.obj;
            Type type = new TypeToken<List<Video>>(){}.getType();
            mVideo = mGson.fromJson(json,type);
            mAdatper = new HomeAdatper(getContext(),mVideo);
            mRecylerView.setAdapter(mAdatper);
            mRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));

        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gaoxiao_fragment, null);
        mRecylerView = view.findViewById(R.id.recycler_view);
        initRequest();
        return view;
    }

    private void initRequest() {

        String url = "https://www.glyac.com/aiyou/index.php?a=getVideoType&videotype=3";

        OkHttpClient mOkHttp = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        mOkHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                   if (response.isSuccessful())
                   {
                       String json = response.body().string();
                       Log.i("TAG",json);
                       Message message = new Message();
                       message.obj = json;
                       mHandler.sendMessage(message);
                   }
            }
        });
    }
}
