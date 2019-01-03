package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.adapter.ProductImageDetailAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ProductRecommendViewHolder;
import com.gtv.hanhee.testlayout.ui.contract.ProductDetailContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.customview.GlideImageLoader;
import com.gtv.hanhee.testlayout.ui.customview.ProductRecommendBanner;
import com.gtv.hanhee.testlayout.ui.presenter.ProductDetailPresenter;
import com.gtv.hanhee.testlayout.utils.FormatUtils;
import com.gtv.hanhee.testlayout.utils.ImageUtils;
import com.gtv.hanhee.testlayout.utils.SharedPreferencesUtil;
import com.gtv.hanhee.testlayout.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

public class ProductDetailActivity extends BaseActivity implements ProductDetailContract.View, OnItemRvClickListener<Object> {

    @Inject
    ProductDetailPresenter productDetailPresenter;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.bannerImage)
    Banner bannerImage;
    @BindView(R.id.bannerRecommend)
    ProductRecommendBanner bannerRecommend;
    @BindView(R.id.txtTag)
    TextView txtTag;
    @BindView(R.id.txtProductName)
    TextView txtProductName;
    @BindView(R.id.txtTag2)
    TextView txtTag2;
    @BindView(R.id.txtTag3)
    TextView txtTag3;
    @BindView(R.id.txtDiscountPrice)
    TextView txtDiscountPrice;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.txtDiscountTime)
    TextView txtDiscountTime;
    @BindView(R.id.txtShopName)
    TextView txtShopName;
    @BindView(R.id.txtShipper)
    TextView txtShipper;
    @BindView(R.id.imgShop)
    ImageView imgShop;
    @BindView(R.id.txtPriceBuyNow)
    TextView txtPriceBuyNow;

    private List<List<Product>> listBannerRecommend = new ArrayList<>();
    private ProductImageDetailAdapter productImageDetailAdapter;
    private List<Product> currentListProduct = new ArrayList<>();
    private List<Product> recommendProductList = new ArrayList<>();
    private Product product;
    private String productId;
    private static final String PRODUCT_ID = "productId";
    private List<Product> cartProductList = new ArrayList<>();

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
        productDetailPresenter.getProduct(token, productId);
        productDetailPresenter.getRecommendProductList(token, productId, 0, 18);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (productDetailPresenter != null) {
            productDetailPresenter.detachView();
        }
    }

    @Override
    public void configViews() {
//        Setting RefreshLayout -----------------
        refreshLayout.setRefreshHeader(new CustomFragmentHeader(mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                productDetailPresenter.getProduct(token, productId);
                productDetailPresenter.getRecommendProductList(token, productId, 0, 18);
                ;
            }
        });

//        Setting Banner ---------------
        bannerImage.setImageLoader(new GlideImageLoader(this));
        bannerImage.setBannerStyle(BannerConfig.NUM_INDICATOR);
        bannerImage.setIndicatorGravity(BannerConfig.RIGHT);

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


    @OnClick(R.id.btnBack)
    public void onBack() {
        onBackPressed();
    }

    @OnClick(R.id.btnSetting)
    public void onSetting() {

    }

    @OnClick(R.id.btnViewShopProduct)
    public void viewShopProduct() {
        ShopActivity.startActivity(this, product.getShop().getId());
    }

    @OnClick(R.id.btnAddToCart)
    public void addToCart() {

    }

    @OnClick(R.id.btnBuyNow)
    public void buyNow() {
        Random rand1 = new Random();
        Random rand2 = new Random();
        int n1 = rand1.nextInt(15) + 1;
        int n2 = rand2.nextInt(4) + 1;
        product.setId(String.valueOf(n1));
        product.getShop().setId(String.valueOf(n2));
        product.getShop().setName(String.valueOf("Shop "+n2));
        Completable.fromAction(() -> MyApplication.mProductDao.insertAll(product)).subscribeOn(Schedulers.io())
                .subscribe();
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
        Log.d("kiemtraProduct", product.getId()+" - "+product.getShop().getId());

    }

    @OnClick(R.id.btnFavourite)
    public void clickFavorite() {

//        List<Product> favorProductList = SharedPreferencesUtil.getInstance().getObject("FavorListProduct", null);
//        favorProductList.add(product);
//        SharedPreferencesUtil.getInstance().putObject("FavoriteProduct", favorProductList);
    }
    @OnClick(R.id.btnHome)
    public void clickHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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

//        Setting data ----------
        txtTag.setText(product.getSubCategory().get(0).getName());
        txtTag2.setText(product.getSubCategory().get(1).getName());
        txtTag3.setText(product.getSubCategory().get(2).getName());
        txtProductName.setText(product.getName());
        txtDiscountPrice.setText(StringUtils.formatPrice(product.getDiscountPrice()+""));
        txtPrice.setText(StringUtils.formatPrice(product.getPrice()+""));
//        txtDiscountTime.setText(StringUtils.formatPrice(product.getPrice()+""));
        txtShopName.setText(product.getShop().getName());
        txtShipper.setText(product.getShop().getShipper());
        txtPriceBuyNow.setText(StringUtils.formatPrice(product.getDiscountPrice()+""));
        ImageUtils.loadImageByGlideWithResize(this, product.getShop().getAvatar(), imgShop, 300, 300);
    }

    @Override
    public void showRecommendProductList(List<Product> recommendProductListResult) {
        recommendProductList.clear();
        recommendProductList.addAll(recommendProductListResult);
        listBannerRecommend.clear();
        int startPos = 1;
        boolean isFinishPage = true;
        currentListProduct.clear();

        for (int i=0; i<recommendProductList.size(); i++) {
            isFinishPage = false;
            currentListProduct.add(recommendProductList.get(i));
            if (i>=6*startPos-1) {
                listBannerRecommend.add(currentListProduct);
                currentListProduct = new ArrayList<>();
                startPos++;
                isFinishPage = true;
            }
        }

        if (!isFinishPage) {
            listBannerRecommend.add(currentListProduct);
        }

        bannerRecommend.setPages(listBannerRecommend, new MZHolderCreator<ProductRecommendViewHolder>() {
            @Override
            public ProductRecommendViewHolder createViewHolder() {
                return new ProductRecommendViewHolder();
            }
        });

//            Intent intent = new Intent(mContext, ImageReviewActivity.class);
//            intent.putStringArrayListExtra("images", (ArrayList<String>) postDetails.getImages());
//            intent.putExtra("type", "multi");
//            mContext.startActivity(intent);
    }
}
