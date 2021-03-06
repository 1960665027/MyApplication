package com.example.administrator.myapplication.fragment;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.adatper.HomeAdatper;
import com.example.administrator.myapplication.bean.Video;
import com.example.administrator.myapplication.view.PalyActivity;
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


public class HomeFragment extends Fragment {

    private RecyclerView mRecylerView;
    private  String url = "https://www.glyac.com/aiyou/index.php?a=getAllVideo";
    private Gson mGson = new Gson();
    private List<Video> mVideo;
    private SwipeRefreshLayout mSwipe;
    private HomeAdatper mAdatper;
    private Context mContext;
    private Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String)msg.obj;
            Type type = new TypeToken<List<Video>>(){}.getType();
            mVideo = mGson.fromJson(json,type);
           mAdatper = new HomeAdatper(mContext,mVideo);
           mRecylerView.setAdapter(mAdatper);
           mRecylerView.setLayoutManager(new LinearLayoutManager(mContext));
           mAdatper.setOnItemClickListenner(new HomeAdatper.OnItemClickListenner() {
               @Override
               public void OnClick(View view, int position, String videopath,int id) {
                  // Toast.makeText(getContext(),"view:"+view+"position:"+position+"city:"+city,Toast.LENGTH_LONG).show();
                   Intent palyIntent = new Intent(mContext, PalyActivity.class);

                   //用Bundle携带数据
                   Bundle bundle=new Bundle();
                   //传递name参数为tinyphp
                   bundle.putString("_ID", videopath);
                   palyIntent.putExtras(bundle);
                   startActivity(palyIntent);
               }
           });

        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
       mRecylerView = view.findViewById(R.id.recycler_view);
       mContext = view.getContext();
       requestVideo();
       initSwipe(view);
        return view;
    }

    private void initSwipe(View view) {
        mSwipe = view.findViewById(R.id.mSwipe);
        mSwipe.setColorSchemeResources(R.color.red);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestVideo();
            }
        });
    }

    private void requestVideo() {

        final OkHttpClient mClient = new OkHttpClient();
        Request mRequest = new Request.Builder()
                .url(url)
                .build();
        mClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipe.setRefreshing(false);
                        Toast.makeText(mContext,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 if(response.isSuccessful())
                 {
                     String json = response.body().string();
                     Message message = new Message();
                     message.obj = json;
                     mHandler.sendMessage(message);

                     mHandler.post(new Runnable() {
                         @Override
                         public void run() {
                             mSwipe.setRefreshing(false);
                             Toast.makeText(mContext,"加载成功",Toast.LENGTH_SHORT).show();
                         }
                     });
                 }else{
                     mHandler.post(new Runnable() {
                         @Override
                         public void run() {
                             mSwipe.setRefreshing(false);
                             Toast.makeText(mContext,"加载失败",Toast.LENGTH_SHORT).show();
                         }
                     });
                 }
            }
        });

    }

}
