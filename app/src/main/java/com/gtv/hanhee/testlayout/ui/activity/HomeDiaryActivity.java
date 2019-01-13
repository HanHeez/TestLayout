package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.adapter.CommunityFlycoTabLayoutAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopAdapter;
import com.gtv.hanhee.testlayout.ui.contract.HomeDiaryContract;
import com.gtv.hanhee.testlayout.ui.customview.fadetoolbar.ObservableScrollView;
import com.gtv.hanhee.testlayout.ui.customview.fadetoolbar.ObservableScrollViewCallbacks;
import com.gtv.hanhee.testlayout.ui.customview.fadetoolbar.ScrollState;
import com.gtv.hanhee.testlayout.ui.customview.fadetoolbar.ScrollUtils;
import com.gtv.hanhee.testlayout.ui.presenter.HomeDiaryPresenter;
import com.gtv.hanhee.testlayout.ui.presenter.HomeRemindPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeDiaryActivity extends BaseActivity implements HomeDiaryContract.View, OnItemRvClickListener<Object>, ObservableScrollViewCallbacks {

    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.rlToolbar)
    RelativeLayout rlToolbar;
    @BindView(R.id.obsScrollView)
    ObservableScrollView obsScrollView;

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnAddDiary)
    ImageView btnAddDiary;
    @BindView(R.id.divider)
    View divider;

    @Inject
    HomeDiaryPresenter mPresenter;
    private ShopAdapter shopAdapter;
    private List<Product> shopProductList;
    private static final String SHOP_ID = "shopId";
    private String shopId;
    private List<Fragment> mFragments;
    CommunityFlycoTabLayoutAdapter communityFlycoTabLayoutAdapter;
    String[] mTitles;

    private int mParallaxEndFadeHeight;
    private int mParallaxStartFadeHeight;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home_diary;
    }

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, HomeDiaryActivity.class);
        intent.putExtra(SHOP_ID, shopId);
        context.startActivity(intent);
    }


    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            shopId = savedInstanceState.getString(SHOP_ID);
        } else {
            shopId = getIntent().getStringExtra(SHOP_ID);
        }
    }

    @Override
    public void initToolBar() {

    }
//    @OnClick(R.id.btnBack)
//    public void onBack() {
//        onBackPressed();
//    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
//        showLoadingScreen(rootView);
        mParallaxEndFadeHeight = 250;
        mParallaxStartFadeHeight = 150;
        onRefreshing();
    }

    //    onRefreshing data ------------

    @Override
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
//            mPresenter.getProduct(token, productId);

    }

    //    show error screen -------------
    @Override
    public void showError() {
        isErrorData = true;
        showErrorScreen(rootView);

    }

    //    click loading screen -----------
    @Override
    public void onSkeletonViewClick(View view) {
        switch (view.getId()) {
            case R.id.page_tip_eventview:
                onRefreshing();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }


    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.white);
        if (scrollY > mParallaxStartFadeHeight) {
            float alpha = Math.min(1, (float) (scrollY-mParallaxStartFadeHeight) / (mParallaxEndFadeHeight-mParallaxStartFadeHeight));
            rlToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
            txtTitle.setTextColor(getResources().getColor(R.color.black));
            btnAddDiary.setImageDrawable(getResources().getDrawable(R.drawable.nav_btn_post_black));
            btnBack.setImageDrawable(getResources().getDrawable(R.drawable.baby_icon_nav_back));
        } else {
            txtTitle.setTextColor(getResources().getColor(R.color.white));
            rlToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.white)));
            btnAddDiary.setImageDrawable(getResources().getDrawable(R.drawable.nav_btn_post));
            btnBack.setImageDrawable(getResources().getDrawable(R.drawable.baby_icon_back));

        }

        if (scrollY >= mParallaxEndFadeHeight) {
            divider.setVisibility(View.VISIBLE);
        } else {
            divider.setVisibility(View.GONE);
        }

//        ViewHelper.setTranslationY(txtImage, -scrollY / 2);

//        // Translate list background
//        ViewHelper.setTranslationY(mListBackgroundView, Math.max(0, -scrollY + mParallaxImageHeight));
    }



    @Override
    public void configViews() {
        obsScrollView.setScrollViewCallbacks(this);
        divider.setVisibility(View.GONE);
        rlToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.white)));

//        Setting RefreshLayout -----------------
//        refreshLayout.setRefreshHeader(new CustomFragmentHeader(mContext));
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                refreshLayout.finishRefresh(1000/*,false*/);
//                ;
//            }
//        });


//        Setting tablayout ----------------------
        mFragments = new ArrayList<>();
        mTitles = mContext.getResources().getStringArray(R.array.shop_detail_tab);
        communityFlycoTabLayoutAdapter = new CommunityFlycoTabLayoutAdapter(getSupportFragmentManager(), mFragments, mTitles);

//        mTabLayout.setTabData(mTabEntities, this, R.id.fl_change, mFragments);

//        Setting Recycler View ---------------
        shopProductList = new ArrayList<>();
//        shopAdapter = new ShopAdapter(this, shopProductList, this);
//        rvCart.setHasFixedSize(true);
//        rvCart.setNestedScrollingEnabled(false);
//        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(mContext);
//        rvCart.setLayoutManager(layoutManagerNews);
//        rvCart.setAdapter(cartAdapter);
//        cartAdapter.setOnItemClickListener((adapter, view, position) -> {
//            if (cartProductSectionList.get(position).isHeader) {
//
//            } else {
//                ProductDetailActivity.startActivity(mContext, cartProductSectionList.get(position).t.getId());
//            }
//        });
    }



    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }
    @Override
    public void complete() {
    }


    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}




