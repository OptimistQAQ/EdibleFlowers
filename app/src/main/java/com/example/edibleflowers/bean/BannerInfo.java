package com.example.edibleflowers.bean;

import com.stx.xhb.xbanner.entity.BaseBannerInfo;

public class BannerInfo implements BaseBannerInfo {

    private String imgUrl;
    private String title;

    public BannerInfo() {
    }

    public BannerInfo(String imgUrl, String title) {
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Object getXBannerUrl() {
        return this.imgUrl;
    }

    @Override
    public String getXBannerTitle() {
        return this.title;
    }
}
