package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.adapter.ProductImageDetailAdapter;
import com.gtv.hanhee.testlayout.ui.contract.ProductDetailContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomBanner;
import com.gtv.hanhee.testlayout.ui.customview.GlideImageLoader;
import com.gtv.hanhee.testlayout.ui.presenter.ProductDetailPresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductDetailActivity extends BaseActivity implements ProductDetailContract.View, OnItemRvClickListener<Object> {

    @Inject
    ProductDetailPresenter productDetailPresenter;

    @BindView(R.id.bannerImage)
    Banner bannerImage;
    @BindView(R.id.bannerRecommend)
    CustomBanner bannerRecommend;

    private ProductImageDetailAdapter productImageDetailAdapter;

    private List<Product> recommendProductList = new ArrayList<>();
    private Product product;
    private String productId;
    private static final String PRODUCT_ID = "productId";

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    public static void startActivity(Context context, String productId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(PRODUCT_ID, productId);
        context.startActivity(intent);
    }


    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            productId = savedInstanceState.getString(PRODUCT_ID);
        } else {
            productId = getIntent().getStringExtra(PRODUCT_ID);
        }
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {
        activityComponent().inject(this);
        productDetailPresenter.attachView(this);
        productDetailPresenter.getProduct(token, "1");
    }

    @Override
    public void configViews() {

//        Setting Banner ---------------

        bannerImage.setImageLoader(new GlideImageLoader(this));
        bannerImage.setBannerStyle(BannerConfig.NUM_INDICATOR);
        bannerImage.setIndicatorGravity(BannerConfig.RIGHT);

        bannerRecommend.setImageLoader(new GlideImageLoader(this));
        bannerRecommend.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        bannerRecommend.setIndicatorGravity(BannerConfig.RIGHT);

//
//        mainAdapter = new MainAdapter(this, holderObjectList, this);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        mDecoration = new ItemOffsetDecoration(this);
//        recyclerView.addItemDecoration(mDecoration);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (productDetailPresenter != null) {
            productDetailPresenter.detachView();
        }
    }

    @OnClick(R.id.btnBack)
    public void onBack() {
        onBackPressed();
    }
    @OnClick(R.id.btnSetting)
    public void onSetting() {

    }

    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }

    @Override
    public void showProduct(Product productResult) {
        product = productResult;
        bannerImage.setImages(product.getThumbnails());
        bannerImage.isAutoPlay(false);
        bannerImage.start();
        bannerImage.setOnBannerListener(position -> {
//            Intent intent = new Intent(mContext, ImageReviewActivity.class);
//            intent.putStringArrayListExtra("images", (ArrayList<String>) postDetails.getImages());
//            intent.putExtra("type", "multi");
//            mContext.startActivity(intent);
        });
    }

    @Override
    public void showRecommendProductList(List<Product> recommendProductListResult) {
        recommendProductList.clear();
        recommendProductList.addAll(recommendProductListResult);

        bannerRecommend.setData(recommendProductList);
        bannerRecommend.isAutoPlay(false);
        bannerRecommend.start();
        bannerRecommend.setOnBannerListener(position -> {
//            Intent intent = new Intent(mContext, ImageReviewActivity.class);
//            intent.putStringArrayListExtra("images", (ArrayList<String>) postDetails.getImages());
//            intent.putExtra("type", "multi");
//            mContext.startActivity(intent);
        });
    }
}
