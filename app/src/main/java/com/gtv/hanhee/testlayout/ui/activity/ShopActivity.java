package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.gtv.hanhee.testlayout.ui.adapter.ShopAdapter;
import com.gtv.hanhee.testlayout.ui.contract.CartContract;
import com.gtv.hanhee.testlayout.ui.contract.ShopContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.presenter.CartPresenter;
import com.gtv.hanhee.testlayout.ui.presenter.ShopPresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ShopActivity extends BaseActivity implements ShopContract.View, OnItemRvClickListener<Object> {
//    @BindView(R.id.refreshLayout)
//    RefreshLayout refreshLayout;
//    @BindView(R.id.rvCart)
//    RecyclerView rvCart;
//

    @Inject
    ShopPresenter shopPresenter;
    private ShopAdapter shopAdapter;
    private List<Product> shopProductList;
    private static final String SHOP_ID = "shopId";
    private String shopId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop;
    }

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, ShopActivity.class);
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
        refreshLayout.setRefreshHeader(new CustomFragmentHeader(mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                ;
            }
        });

//        Setting Recycler View ---------------
        shopProductList = new ArrayList<>();
        shopAdapter = new ShopAdapter(this, shopProductList, this);
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
}
