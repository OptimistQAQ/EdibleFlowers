package com.example.edibleflowers.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.edibleflowers.R;
import com.example.edibleflowers.bean.BannerInfo;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ZiXunFragment extends Fragment {

    private View root;
    private XBanner xBanner;

    // 测试用图片 url
    private final String baiduLogoImageUrl = "https://www.baidu.com/img/flexible/logo/pc/result@2.png";
    private final String bingDailyImageUrl = "https://cn.bing.com/th?id=OHR.VosgesBioReserve_ZH-CN4762694302_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp";
    private final String appleImageUrl = "https://www.apple.com.cn/home/heroes/cny-2021-film/images/cny__gaectlu0tiai_mediumtall.jpg";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_zixun, container, false);
        initView();
        initData();
        initBanner();
        return root;
    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        xBanner = root.findViewById(R.id.zixun_banner);
//        精选 分类 新闻
    }

    /**
     * 初始化轮播 banner
     * https://github.com/xiaohaibin/XBanner
     */
    private void initBanner()
    {
        // 设置图片点击事件
        xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(getContext(),
                        "点击了第" + (position + 1) + "图片  " + ((BannerInfo) model).getTitle(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
        // 加载图片
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                BannerInfo bannerImage = ((BannerInfo) model);
                String url = bannerImage.getXBannerUrl();
                Glide.with(ZiXunFragment.this)
                        .load(url)
                        .into((ImageView) view);
            }
        });
        xBanner.setPageTransformer(Transformer.Alpha);
    }

    /**
     * 初始化数据
     */
    private void initData()
    {
        // 连接服务器获取 banner 信息 下面数据用于演示


        List<BannerInfo> demoData = new ArrayList<>();

        BannerInfo baidu = new BannerInfo(baiduLogoImageUrl, "Baidu");
        BannerInfo bing  = new BannerInfo(bingDailyImageUrl, "Bing");
        BannerInfo apple = new BannerInfo(appleImageUrl, "Apple");

        demoData.add(baidu);
        demoData.add(bing);
        demoData.add(apple);

        xBanner.setBannerData(demoData);
        xBanner.setAutoPlayAble(demoData.size() > 1);
    }

    @Override
    public void onResume() {
        super.onResume();
        xBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xBanner.stopAutoPlay();
    }
}
