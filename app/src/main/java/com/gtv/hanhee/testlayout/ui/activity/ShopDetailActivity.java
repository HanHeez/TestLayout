package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Category;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.adapter.CommunityFlycoTabLayoutAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopAdapter;
import com.gtv.hanhee.testlayout.ui.contract.ShopContract;
import com.gtv.hanhee.testlayout.ui.fragment.ShopCategoryFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ShopHomeFragment;
import com.gtv.hanhee.testlayout.ui.presenter.ShopPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ShopDetailActivity extends BaseActivity implements ShopContract.View, OnItemRvClickListener<Object> {
//    @BindView(R.id.refreshLayout)
//    RefreshLayout refreshLayout;
//    @BindView(R.id.rvCart)
//    RecyclerView rvCart;
//

    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Inject
    ShopPresenter shopPresenter;
    private ShopAdapter shopAdapter;
    private List<Product> shopProductList;
    private static final String SHOP_ID = "shopId";
    private String shopId;

    CommunityFlycoTabLayoutAdapter communityFlycoTabLayoutAdapter;
    String[] mTitles;
    private int[] mIconUnselectIds;
    private int[] mIconSelectIds;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_detail;
    }

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, ShopDetailActivity.class);
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

    @Override
    public void initDatas() {
        activityComponent().inject(this);
        shopPresenter.attachView(this);
//        shopPresenter.getCartProduct();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (shopPresenter != null) {
            shopPresenter.detachView();
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

//        Setting tablayout ----------------------

        mTitles = mContext.getResources().getStringArray(R.array.shop_detail_tab);
        mFragments.clear();
        mFragments.add(new ShopHomeFragment());
        mFragments.add(new ShopCategoryFragment());
        mFragments.add(new ShopCategoryFragment());
        communityFlycoTabLayoutAdapter = new CommunityFlycoTabLayoutAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(communityFlycoTabLayoutAdapter);
        tabLayout.setViewPager(viewPager);

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
    public void showError() {
    }

    @Override
    public void complete() {
    }

    @Override
    public void showListCategory(List<Category> categoryListResult) {

    }
}
