package com.example.administrator.myapplication.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.myapplication.R;

public class HotFragment extends Fragment {
    private TabLayout mTablayout;
    private ViewPager mViewpager;
    private String[] mTitles = {"搞笑", "游戏", "音乐","开眼", "生活", "科技","其他"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_fragment, null);
        initView(view);
        return  view;
    }
    private void initView(View view) {
        mTablayout =  view.findViewById(R.id.tablayout);
        mViewpager =  view.findViewById(R.id.viePager);

        mViewpager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }


            @Override
            public Fragment getItem(int position) {
                if (position == 0)
                    return new GaoXiaoFragment();
                else if (position == 1)
                    return new YouXiFragment();
                else if (position == 2)
                    return new YinYueFragment();
                else if (position == 3)
                    return new KaiYanFragment();
                else if (position == 4)
                    return new ShengHuoFragment();
                else if (position == 5)
                    return new KeJiFragment();
                return new QiTaFragment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });

        mTablayout.setupWithViewPager(mViewpager);
    }

}
