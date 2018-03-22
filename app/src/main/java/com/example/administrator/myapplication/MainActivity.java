package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.myapplication.bean.Tab;
import com.example.administrator.myapplication.fragment.FollowFragment;
import com.example.administrator.myapplication.fragment.HomeFragment;
import com.example.administrator.myapplication.fragment.HotFragment;
import com.example.administrator.myapplication.fragment.MyFragment;
import com.example.administrator.myapplication.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Tab> listTab = new ArrayList<>(4);
    private LayoutInflater mInflater;
    private FragmentTabHost mTabHost;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        initTab();
        initToolbar();
    }
    private void initTab()
    {
        //初始化数据
        Tab home_tab = new Tab(R.string.home,R.drawable.selector_icon_home,HomeFragment.class);
        Tab hot_tab = new Tab(R.string.hot,R.drawable.selector_icon_hot,HotFragment.class);
        Tab follow_tab = new Tab(R.string.follow,R.drawable.selector_icon_follow,FollowFragment.class);
        Tab my_tab = new Tab(R.string.my,R.drawable.selector_icon_my,MyFragment.class);
        listTab.add(home_tab);
        listTab.add(hot_tab);
        listTab.add(follow_tab);
        listTab.add(my_tab);
        mTabHost = this.findViewById(android.R.id.tabhost);
        mInflater = LayoutInflater.from(this);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);


        for (Tab tab :listTab)
        {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));

            tabSpec.setIndicator(buildIndicator(tab));
            mTabHost.addTab(tabSpec,tab.getFragment(),null);
        }
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTab(0);


    }
    private View buildIndicator(Tab tab)
    {
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        ImageView imageView = view.findViewById(R.id.icon_tab);
        TextView textView = view.findViewById(R.id.txt_indicator);

        imageView.setBackgroundResource(tab.getIcon());
        textView.setText(tab.getTitle());
        return view;
    }
    /**
     * toolbar的按钮
     */
    private void initToolbar()
    {
        mToolbar = this.findViewById(R.id.toolbar);
        mToolbar.findViewById(R.id.video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"上传视频", Toast.LENGTH_LONG).show();
            }
        });
        mToolbar.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"搜索",Toast.LENGTH_LONG).show();
            }
        });
        mToolbar.findViewById(R.id.caidan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"扫一扫",Toast.LENGTH_LONG).show();
            }
        });

    }

}
