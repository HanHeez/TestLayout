package com.gtv.hanhee.testlayout.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseFragment;
import com.gtv.hanhee.testlayout.model.BannerDetail;
import com.gtv.hanhee.testlayout.ui.contract.ShopHomeContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.customview.GlideImageLoader;
import com.gtv.hanhee.testlayout.ui.presenter.ShopHomePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopHomeFragment extends BaseFragment implements ShopHomeContract.View {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.banner)
    Banner banner;


    @Inject
    ShopHomePresenter shopHomePresenter;
    @Override

    public int getLayoutResId() {
        return R.layout.fragment_shop_home;
    }


    @Override
    public void attachView() {
        activityComponent().inject(this);
        shopHomePresenter.attachView(this);
    }

    @Override
    public void initDatas() {
        shopHomePresenter.getShopBanner(token);
    }

    @Override
    public void configViews() {

//        Setting RefreshLayout -----------------

        refreshLayout.setRefreshHeader(new CustomFragmentHeader(mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                shopHomePresenter.getShopBanner(token);
                ;
            }
        });

        banner.setImageLoader(new GlideImageLoader(mContext));
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    @Override
    public void showShopBanner(List<BannerDetail> bannerDetailListResult) {

        List<String> images = new ArrayList<>();

        for (int i=0;i<bannerDetailListResult.size();i++) {
            images.add(bannerDetailListResult.get(i).getImage());
        }

        banner.setImages(images);
        banner.isAutoPlay(true);
        banner.start();
        banner.setOnBannerListener(position -> {
//            Intent intent = new Intent(mContext, ImageReviewActivity.class);
//            intent.putStringArrayListExtra("images", (ArrayList<String>) postDetails.getImages());
//            intent.putExtra("type", "multi");
//            mContext.startActivity(intent);
        });
    }

    private void settingBannerImageView() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }
}
