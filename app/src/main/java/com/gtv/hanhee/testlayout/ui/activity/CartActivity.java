package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ProductSection;
import com.gtv.hanhee.testlayout.ui.adapter.CartAdapter;
import com.gtv.hanhee.testlayout.ui.contract.CartContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.presenter.CartPresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CartActivity extends BaseActivity implements CartContract.View, OnItemRvClickListener<Object> {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rvCart)
    RecyclerView rvCart;
    @BindView(R.id.rvRecommend)
    RecyclerView rvRecommend;

    @Inject
    CartPresenter cartPresenter;
    private CartAdapter cartAdapter;
    private List<Product> cartProductList;
    private List<ProductSection> cartProductSectionList;
    private static final String PRODUCT_ID = "productId";
    private String productId = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_cart;
    }

    public static void startActivity(Context context, String productId) {
        Intent intent = new Intent(context, CartActivity.class);
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
        cartPresenter.attachView(this);
        cartPresenter.getCartProduct();
        cartProductSectionList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cartPresenter != null) {
            cartPresenter.detachView();
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
                ;
            }
        });

//        Setting Recycler View ---------------
        cartProductList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, cartProductSectionList, this);
        rvCart.setHasFixedSize(true);
        rvCart.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(mContext);
        rvCart.setLayoutManager(layoutManagerNews);
        rvCart.setAdapter(cartAdapter);
        cartAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (cartProductSectionList.get(position).isHeader) {

            } else {
                ProductDetailActivity.startActivity(mContext, cartProductSectionList.get(position).t.getId());
            }
        });
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

    private List<Product> productSortList = new ArrayList<>();

    @Override
    public void showCartProduct(List<Product> productList) {
        if (productList.size() > 0) {
            for (int i = 0; i < productList.size(); i++) {
                Log.d("kiemtra1", productList.get(i).getId() + " - " + productList.get(i).getShop().getId());
            }

            productSortList.clear();
            String currentShopId = productList.get(0).getShop().getId();
            cartProductSectionList.clear();
            cartProductSectionList.add(new ProductSection(true, productList.get(0).getShop().getName()));
            while (productList.size() > 0) {
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getShop().getId().equals(currentShopId)) {
                        cartProductSectionList.add(new ProductSection(productList.get(i)));
                        productSortList.add(productList.get(i));
                        productList.remove(i);
                        i--;
                    }
                }
                if (productList.size() > 0) {
                    currentShopId = productList.get(0).getShop().getId();
                    cartProductSectionList.add(new ProductSection(true, productList.get(0).getShop().getName()));
                }
            }
            for (int i = 0; i < cartProductSectionList.size(); i++) {
                if (cartProductSectionList.get(i).isHeader) {
                    Log.d("kiemtra2", "shop name: " + cartProductSectionList.get(i).header);
                } else {
                    Log.d("kiemtra2", cartProductSectionList.get(i).t.getId() + " - " + cartProductSectionList.get(i).t.getShop().getId());
                }
            }

            cartAdapter.notifyDataSetChanged();
        }
    }
}
