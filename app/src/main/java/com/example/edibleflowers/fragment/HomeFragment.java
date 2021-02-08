package com.example.edibleflowers.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edibleflowers.R;
import com.example.edibleflowers.fragment.home.HomeDailyRecommendFragment;
import com.example.edibleflowers.fragment.home.HomeScanFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private View root;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ViewPager mViewPager;
    private SegmentTabLayout mTabLayout;
    private final String[] mTitles = {"每日推荐", "花卉识别",};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        initData();
        return root;
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        mFragments.add(new HomeDailyRecommendFragment()); // 每日推荐
        mFragments.add(new HomeScanFragment());           // 花卉识别

        mViewPager = root.findViewById(R.id.home_view_pager);
        mViewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));

        mTabLayout = root.findViewById(R.id.home_segment_tab_layout);
        mTabLayout.setTabData(mTitles);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                mViewPager.setCurrentItem(position);
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                mTabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);
        mTabLayout.showDot(1); // 显示小红点
    }


    /**
     * 初始化数据
     */
    private void initData()
    {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
