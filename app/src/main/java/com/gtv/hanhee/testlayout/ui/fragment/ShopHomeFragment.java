package com.gtv.hanhee.testlayout.ui.fragment;


import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseFragment;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ShopBanner;
import com.gtv.hanhee.testlayout.ui.activity.CartActivity;
import com.gtv.hanhee.testlayout.ui.activity.MainActivity;
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
import butterknife.OnClick;

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
    @BindView(R.id.imgStyleRv)
    ImageView imgStyleRv;
    @BindView(R.id.rootView)
    LinearLayout rootView;

    private ShopHomeRowAdapter shopHomeRowAdapter;
    private ShopHomeGridAdapter shopHomeGridAdapter;
    private Handler handler = new Handler();

    private List<Product> productList;
    @Inject
    ShopHomePresenter shopHomePresenter;
    private LinearLayoutManager rowLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private boolean isGrid = false;
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
        handler.removeCallbacksAndMessages(null);
        if (shopHomePresenter != null) {
            shopHomePresenter.detachView();
        }
    }

    @Override
    public void initDatas() {
        showLoadingScreen(rootView);
        onRefreshing();
    }

    @Override
    public void configViews() {

//        Setting RefreshLayout -----------------

        refreshLayout.setRefreshHeader(new CustomFragmentHeader(mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                onRefreshing();
                ;
            }
        });

//        Setting RecyclerView ----------------
        productList = new ArrayList<>();
        shopHomeRowAdapter = new ShopHomeRowAdapter(activity, productList, this);
        shopHomeGridAdapter = new ShopHomeGridAdapter(activity, productList, this);
        gridLayoutManager = new GridLayoutManager(mContext, 2);
        rowLayoutManager = new LinearLayoutManager(mContext);

        rvShopHome.setHasFixedSize(true);
        rvShopHome.setNestedScrollingEnabled(false);

        rvShopHome.setLayoutManager(rowLayoutManager);
        rvShopHome.setAdapter(shopHomeRowAdapter);

//        set Empty view ------------------
//        View emptyView = getLayoutInflater().inflate(R.layout.layout_empty_cart_view, (ViewGroup) rvShopHome.getParent(), false);
//
//        shopHomeRowAdapter.setEmptyView(R.layout.layout_empty_cart_view, (ViewGroup) rvShopHome.getParent());
//        shopHomeGridAdapter.setEmptyView(R.layout.layout_empty_cart_view, (ViewGroup) rvShopHome.getParent());
//        emptyView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("kiemtra", "onclick");
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//            }
//        });


        shopHomeRowAdapter.setOnItemClickListener((adapter, view, position) -> {
            ProductDetailActivity.startActivity(mContext, productList.get(position).getId());
        });

        shopHomeGridAdapter.setOnItemClickListener((adapter, view, position) -> {
            ProductDetailActivity.startActivity(mContext, productList.get(position).getId());
        });

        gridLayoutManager = new GridLayoutManager(mContext, 2);
//        rvShopHome.setLayoutManager(gridLayoutManager);
//        rvShopHome.setAdapter(shopHomeGridAdapter);

//        Setting Banner -------------------
    }

    private void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        shopHomePresenter.getShopBanner(token, 0, 5);
    }

    private boolean isRow = true;
    @OnClick(R.id.btnStyleRv)
    public void changeStyleRv() {
        if (isRow) {
            rvShopHome.setLayoutManager(gridLayoutManager);
            rvShopHome.setAdapter(shopHomeGridAdapter);
            imgStyleRv.setImageDrawable(getResources().getDrawable(R.drawable.apk_classify_two));
            shopHomeGridAdapter.notifyDataSetChanged();
            isRow = false;
        } else {
            rvShopHome.setLayoutManager(rowLayoutManager);
            rvShopHome.setAdapter(shopHomeRowAdapter);
            imgStyleRv.setImageDrawable(getResources().getDrawable(R.drawable.apk_classify_one));
            shopHomeRowAdapter.notifyDataSetChanged();
            isRow = true;
        }
    }

    @Override
    public void showShopBanner(List<ShopBanner> shopBannerListResult) {
        List<String> images = new ArrayList<>();
        for (int i = 0; i < shopBannerListResult.size(); i++) {
            images.add(shopBannerListResult.get(i).getImageUrl());
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
        shopHomePresenter.getListProductNewest(token, 0, 15);
    }

    @Override
    public void showListProductNewest(List<Product> productListResult) {
        productList.clear();
        productList.addAll(productListResult);
        shopHomeRowAdapter.notifyDataSetChanged();
        shopHomeGridAdapter.notifyDataSetChanged();
        isErrorData = false;
        skeletonScreen.hide();
    }


    @Override
    public void showError() {
        isErrorData = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showErrorScreen(rootView);
            }
        }, 500);
    }

    @Override
    public void complete() {

    }

    @Override
    public void onItemRvClick(View view, Product item, int adapterPosition) {

    }

    @Override
    public void onSkeletonViewClick(View view) {
        switch (view.getId()) {
            case R.id.page_tip_eventview:
                onRefreshing();
                break;
        }
    }
}
