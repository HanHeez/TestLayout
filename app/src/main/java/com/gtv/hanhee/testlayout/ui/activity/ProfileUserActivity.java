package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.adapter.CommunityFlycoTabLayoutAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopAdapter;
import com.gtv.hanhee.testlayout.ui.contract.ProfileUserContract;
import com.gtv.hanhee.testlayout.ui.contract.ShopContract;
import com.gtv.hanhee.testlayout.ui.fragment.ShopCategoryFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ShopFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ShopHomeFragment;
import com.gtv.hanhee.testlayout.ui.presenter.ProfileUserPresenter;
import com.gtv.hanhee.testlayout.ui.presenter.ShopPresenter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ProfileUserActivity extends BaseActivity implements ProfileUserContract.View, OnItemRvClickListener<Object> {

    @BindView(R.id.tabLayout)
    SmartTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Inject
    ProfileUserPresenter mPresenter;
    private ShopAdapter shopAdapter;
    private List<Product> shopProductList;
    private static final String USER_ID = "userId";
    private String userId;

    private CommunityFlycoTabLayoutAdapter communityFlycoTabLayoutAdapter;
    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_profile_user;
    }

    public static void startActivity(Context context, String userId) {
        Intent intent = new Intent(context, ProfileUserActivity.class);
        intent.putExtra(USER_ID, userId);
        context.startActivity(intent);
    }


    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            userId = savedInstanceState.getString(USER_ID);
        } else {
            userId = getIntent().getStringExtra(USER_ID);
        }
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
//        mPresenter.getCartProduct();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void configViews() {
//        Setting RefreshLayout -----------------
//        refreshLayout.setRefreshHeader(new CustomFragmentHeader(mContext));
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                refreshLayout.finishRefresh(1000/*,false*/);
//                ;
//            }
//        });

//        Setting Recycler View ---------------
        mFragments.clear();
        mTitles.clear();
        mTitles.add("Bài viết");
        mTitles.add("Thông tin");
        mFragments.add(new ShopHomeFragment());
        mFragments.add(new ShopHomeFragment());

        communityFlycoTabLayoutAdapter = new CommunityFlycoTabLayoutAdapter(getSupportFragmentManager(), mFragments, mTitles.toArray(new String[mTitles.size()]) );
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(communityFlycoTabLayoutAdapter);
        tabLayout.setViewPager(viewPager);
    }


    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }

    @Override
    public void showError() {
    }

    @Override
    public void complete() {
    }
}

