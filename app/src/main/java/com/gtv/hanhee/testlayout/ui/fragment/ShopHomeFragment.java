package com.gtv.hanhee.testlayout.ui.fragment;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseFragment;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.BannerDetail;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.activity.ProductDetailActivity;
import com.gtv.hanhee.testlayout.ui.adapter.ShopHomeGridAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopHomeRowAdapter;
import com.gtv.hanhee.testlayout.ui.contract.ShopHomeContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.customview.GlideImageLoader;
import com.gtv.hanhee.testlayout.ui.presenter.ShopHomePresenter;
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
public class ShopHomeFragment extends BaseFragment implements ShopHomeContract.View, OnItemRvClickListener<Product> {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rvShopHome)
    RecyclerView rvShopHome;

    private ShopHomeRowAdapter shopHomeRowAdapter;
    private ShopHomeGridAdapter shopHomeGridAdapter;

    private List<Product> productList;
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
    public void onDestroy() {
        super.onDestroy();
        if (shopHomePresenter != null) {
            shopHomePresenter.detachView();
        }
    }

    @Override
    public void initDatas() {
        shopHomePresenter.getShopBanner(token);
        shopHomePresenter.getListProduct(token);
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

//        Setting RecyclerView ----------------
        productList = new ArrayList<>();
        shopHomeRowAdapter = new ShopHomeRowAdapter(activity, productList, this);

        shopHomeGridAdapter = new ShopHomeGridAdapter(activity, productList, this);

        rvShopHome.setHasFixedSize(true);
        rvShopHome.setNestedScrollingEnabled(false);

        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(mContext);
        rvShopHome.setLayoutManager(layoutManagerNews);
        rvShopHome.setAdapter(shopHomeRowAdapter);

        shopHomeRowAdapter.setOnItemClickListener((adapter, view, position) -> {
            ProductDetailActivity.startActivity(mContext, productList.get(position).getId());
        });

        shopHomeGridAdapter.setOnItemClickListener((adapter, view, position) -> {
            ProductDetailActivity.startActivity(mContext, productList.get(position).getId());
        });

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
//        rvShopHome.setLayoutManager(gridLayoutManager);
//        rvShopHome.setAdapter(shopHomeGridAdapter);

//        Setting Banner -------------------

    }

    @Override
    public void showShopBanner(List<BannerDetail> bannerDetailListResult) {
        List<String> images = new ArrayList<>();
        for (int i=0;i<bannerDetailListResult.size();i++) {
            images.add(bannerDetailListResult.get(i).getImage());
        }
        banner.setImageLoader(new GlideImageLoader(mContext));
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
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

    @Override
    public void showListProduct(List<Product> productListResult) {
        productList.clear();
        productList.addAll(productListResult);
        shopHomeRowAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onItemRvClick(View view, Product item, int adapterPosition) {

    }
}
