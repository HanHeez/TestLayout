package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.Shop;
import com.gtv.hanhee.testlayout.ui.adapter.ProductImageDetailAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ProductRecommendViewHolder;
import com.gtv.hanhee.testlayout.ui.contract.ProductDetailContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.customview.GlideImageLoader;
import com.gtv.hanhee.testlayout.ui.customview.ProductRecommendBanner;
import com.gtv.hanhee.testlayout.ui.presenter.ProductDetailPresenter;
import com.gtv.hanhee.testlayout.utils.ImageUtils;
import com.gtv.hanhee.testlayout.utils.SharedPreferencesUtil;
import com.gtv.hanhee.testlayout.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.lnPrice)
    LinearLayout lnPrice;
    @BindView(R.id.txtAmountCart)
    TextView txtAmountCart;

    private List<List<Product>> listBannerRecommend = new ArrayList<>();
    private ProductImageDetailAdapter productImageDetailAdapter;
    private List<Product> currentListProduct = new ArrayList<>();
    private List<Product> recommendProductList = new ArrayList<>();
    private Product product;
    private String productId;
    private static final String PRODUCT_ID = "productId";
    private List<Product> cartProductList = new ArrayList<>();
    private int amountProductCart = 0;

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

//        Setting cart amount -------------

    }

    public void settingAmountCart() {
        if (amountProductCart == 0) {
            txtAmountCart.setVisibility(View.GONE);
        } else {
            txtAmountCart.setVisibility(View.VISIBLE);
            if(amountProductCart>99) {
                txtAmountCart.setText("99+");
            } else txtAmountCart.setText(amountProductCart+"");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        amountProductCart = SharedPreferencesUtil.getInstance().getInt("amountCart", 0);
        settingAmountCart();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @OnClick(R.id.rlGoToCart)
    public void goToCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.btnGoToCart)
    public void goToImgCart() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);

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
        ShopDetailActivity.startActivity(this, product.getShop().getId());
    }

    @OnClick(R.id.btnAddToCart)
    public void addToCart() {
        MyApplication.mProductDao.findProduct(product.getId()).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Product>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Product productResult) {
                        if (productResult.getOrderAmount()<5) {
                            amountProductCart++;
                            SharedPreferencesUtil.getInstance().putInt("amountCart", amountProductCart);
                            settingAmountCart();
                            productResult.setOrderAmount(productResult.getOrderAmount() + 1);
                            Completable.fromAction(() -> MyApplication.mProductDao.insertAll(productResult)).subscribeOn(Schedulers.io())
                                    .subscribe();
                            Toast.makeText(mContext, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
//
                        } else {
                            Toast.makeText(mContext, "Không thể thêm vào giỏ hảng. Số lượng tối đa là 5", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        product.setOrderAmount(1);
                        amountProductCart++;
                        SharedPreferencesUtil.getInstance().putInt("amountCart", amountProductCart);
                        settingAmountCart();
                        product.setCheckedProduct(true);
                        Completable.fromAction(() -> MyApplication.mProductDao.insertAll(product)).subscribeOn(Schedulers.io())
                                .subscribe();
                        Toast.makeText(mContext, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();//
                        Completable.fromAction(() -> MyApplication.mProductDao.updateProducts(product)).subscribeOn(Schedulers.io())
                                .subscribe();
                    }
                });
    }

    @OnClick(R.id.btnBuyNow)
    public void buyNow() {
        OrderActivity.startActivity(this, "buynow", productId);
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
        txtTag.setText(product.getSubCategories().get(0).getName());
        txtTag2.setText(product.getSubCategories().get(1).getName());
        txtTag3.setText(product.getSubCategories().get(2).getName());
        txtProductName.setText(product.getName());
        txtDiscountPrice.setText(StringUtils.formatPrice(product.getPrice() * (100 - product.getDiscountPercent()) / 100 + ""));
        if (product.getDiscountPercent()>0) {
            lnPrice.setVisibility(View.VISIBLE);

        } else {
            lnPrice.setVisibility(View.GONE);
        }
        txtPrice.setText(StringUtils.formatPrice(product.getPrice()+""));
//        txtDiscountTime.setText(StringUtils.formatPrice(product.getPrice()+""));
        txtShopName.setText(product.getShop().getName());
//        txtShipper.setText(product.getShop().getShipper());
        txtPriceBuyNow.setText(StringUtils.formatPrice(product.getPrice()*(100-product.getDiscountPercent())/100+""));
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

    }
}
