package com.gtv.hanhee.testlayout.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseFragment;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.SubCategory;
import com.gtv.hanhee.testlayout.ui.activity.ProductDetailActivity;
import com.gtv.hanhee.testlayout.ui.adapter.ShopHomeGridAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopHomeRowAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopSubCategoryAdapter;
import com.gtv.hanhee.testlayout.ui.contract.ShopCategoryContact;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.presenter.ShopCategoryPresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopCategoryFragment extends BaseFragment implements ShopCategoryContact.View, OnItemRvClickListener<Product> {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rvShopHome)
    RecyclerView rvShopHome;
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;
    @BindView(R.id.imgStyleRv)
    ImageView imgStyleRv;
    @BindView(R.id.rootView)
    LinearLayout rootView;



    private ShopHomeRowAdapter shopHomeRowAdapter;
    private ShopHomeGridAdapter shopHomeGridAdapter;
    private ShopSubCategoryAdapter shopSubCategoryAdapter;

    private LinearLayoutManager rowLayoutManager;
    private GridLayoutManager gridLayoutManager;

    private List<Product> productList;
    private List<SubCategory> subCategoryList;
    private Handler handler = new Handler();
    @Inject
    ShopCategoryPresenter shopCategoryPresenter;

    private String categoryId;
    private static final String CATEGORY_ID = "categoryId";

    public static Fragment newInstance(String  categoryId){
        Bundle bundle = new Bundle();
        bundle.putSerializable(CATEGORY_ID,categoryId);
        Fragment fragment = new ShopCategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initDataGetFromArgument(Bundle savedInstanceState) {
        super.initDataGetFromArgument(savedInstanceState);
        if(savedInstanceState != null){
            categoryId = savedInstanceState.getString(CATEGORY_ID);
        }
        else {
            categoryId = getArguments().getString(CATEGORY_ID);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_shop_category;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        if (shopCategoryPresenter != null) {
            shopCategoryPresenter.detachView();
        }
    }

    @Override
    public void attachView() {
        activityComponent().inject(this);
        shopCategoryPresenter.attachView(this);
    }

    private boolean isRow = false;
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
        subCategoryList = new ArrayList<>();

//        Rv SubCategory -----------------
        shopSubCategoryAdapter = new ShopSubCategoryAdapter(activity, subCategoryList, this);

        rvCategory.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(mContext, 4);
        rvCategory.setLayoutManager(gridLayoutManagerCategory);
        rvCategory.setAdapter(shopSubCategoryAdapter);

//        Rv Product -----------------
        shopHomeRowAdapter = new ShopHomeRowAdapter(activity, productList, this);
        shopHomeGridAdapter = new ShopHomeGridAdapter(activity, productList, this);
        rvShopHome.setNestedScrollingEnabled(false);

//        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(mContext);
//        rvShopHome.setLayoutManager(layoutManagerNews);
//        rvShopHome.setAdapter(shopHomeRowAdapter);

        gridLayoutManager = new GridLayoutManager(mContext, 2);
        rowLayoutManager = new LinearLayoutManager(mContext);
        rvShopHome.setLayoutManager(gridLayoutManager);
        rvShopHome.setAdapter(shopHomeGridAdapter);

        shopHomeRowAdapter.setOnItemClickListener((adapter, view, position) -> {
            ProductDetailActivity.startActivity(mContext, productList.get(position).getId());
        });

        shopHomeGridAdapter.setOnItemClickListener((adapter, view, position) -> {
            ProductDetailActivity.startActivity(mContext, productList.get(position).getId());
        });

    }

    private void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        shopCategoryPresenter.getListSubCategory(token, categoryId, 0 , 8);
    }


    @Override
    public void showListProductByCategory(List<Product> productListResult) {
        isErrorData = false;
        productList.clear();
        productList.addAll(productListResult);
        shopHomeRowAdapter.notifyDataSetChanged();
        shopHomeGridAdapter.notifyDataSetChanged();
        skeletonScreen.hide();

    }

    @Override
    public void showListSubCategory(List<SubCategory> subCategoryListResult) {
        subCategoryList.clear();
        subCategoryList.addAll(subCategoryListResult);
        shopSubCategoryAdapter.notifyDataSetChanged();
        shopCategoryPresenter.getListProductByCategory(token, categoryId, 0, 15);
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
