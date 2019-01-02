package com.gtv.hanhee.testlayout.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseFragment;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.BannerDetail;
import com.gtv.hanhee.testlayout.model.CategoriesNews;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.SubCategory;
import com.gtv.hanhee.testlayout.ui.activity.ProductDetailActivity;
import com.gtv.hanhee.testlayout.ui.adapter.ShopHomeGridAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopHomeRowAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopSubCategoryAdapter;
import com.gtv.hanhee.testlayout.ui.contract.ShopCategoryContact;
import com.gtv.hanhee.testlayout.ui.contract.ShopHomeContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.customview.GlideImageLoader;
import com.gtv.hanhee.testlayout.ui.presenter.ShopCategoryPresenter;
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
public class ShopCategoryFragment extends BaseFragment implements ShopCategoryContact.View, OnItemRvClickListener<Product> {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rvShopHome)
    RecyclerView rvShopHome;
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;

    private ShopHomeRowAdapter shopHomeRowAdapter;
    private ShopHomeGridAdapter shopHomeGridAdapter;
    private ShopSubCategoryAdapter shopSubCategoryAdapter;

    private List<Product> productList;
    private List<SubCategory> subCategoryList;
    @Inject
    ShopCategoryPresenter shopCategoryPresenter;
    @Override

    public int getLayoutResId() {
        return R.layout.fragment_shop_category;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (shopCategoryPresenter != null) {
            shopCategoryPresenter.detachView();
        }
    }

    @Override
    public void attachView() {
        activityComponent().inject(this);
        shopCategoryPresenter.attachView(this);
    }



    @Override
    public void initDatas() {
        shopCategoryPresenter.getListSubCategory(token, "1");
        shopCategoryPresenter.getListProduct(token);
    }

    @Override
    public void configViews() {

//        Setting RefreshLayout -----------------

        refreshLayout.setRefreshHeader(new CustomFragmentHeader(mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
//                shopHomePresenter.getShopBanner(token);
                ;
            }
        });

//        Setting RecyclerView ----------------
        productList = new ArrayList<>();
        subCategoryList = new ArrayList<>();

//        Rv SubCategory -----------------
        shopSubCategoryAdapter = new ShopSubCategoryAdapter(activity, subCategoryList, this);
        rvCategory.setHasFixedSize(true);
        rvCategory.setNestedScrollingEnabled(false);

        GridLayoutManager gridLayoutManagerCategory = new GridLayoutManager(mContext, 4);
        rvCategory.setLayoutManager(gridLayoutManagerCategory);
        rvCategory.setAdapter(shopSubCategoryAdapter);

//        Rv Product -----------------
        shopHomeRowAdapter = new ShopHomeRowAdapter(activity, productList, this);
        shopHomeGridAdapter = new ShopHomeGridAdapter(activity, productList, this);
        rvShopHome.setHasFixedSize(true);
        rvShopHome.setNestedScrollingEnabled(false);

//        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(mContext);
//        rvShopHome.setLayoutManager(layoutManagerNews);
//        rvShopHome.setAdapter(shopHomeRowAdapter);


        GridLayoutManager gridLayoutManagerShop = new GridLayoutManager(mContext, 2);
        rvShopHome.setLayoutManager(gridLayoutManagerShop);
        rvShopHome.setAdapter(shopHomeGridAdapter);

        shopHomeRowAdapter.setOnItemClickListener((adapter, view, position) -> {
            ProductDetailActivity.startActivity(mContext, productList.get(position).getId());
        });

        shopHomeGridAdapter.setOnItemClickListener((adapter, view, position) -> {
            ProductDetailActivity.startActivity(mContext, productList.get(position).getId());
        });

    }


    @Override
    public void showListProduct(List<Product> productListResult) {
        productList.clear();
        productList.addAll(productListResult);
        shopHomeRowAdapter.notifyDataSetChanged();
    }

    @Override
    public void showListSubCategory(List<SubCategory> subCategoryListResult) {
        subCategoryList.clear();
        subCategoryList.addAll(subCategoryListResult);
        shopSubCategoryAdapter.notifyDataSetChanged();
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
