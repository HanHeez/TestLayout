package com.gtv.hanhee.testlayout.ui.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.manager.CheckboxCartEvent;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ProductSection;
import com.gtv.hanhee.testlayout.model.ProductViewModel;
import com.gtv.hanhee.testlayout.ui.adapter.CartAdapter;
import com.gtv.hanhee.testlayout.ui.contract.CartContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.presenter.CartPresenter;
import com.gtv.hanhee.testlayout.utils.RxBus;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

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
    private List<List<Integer>> productByShopList;
//    @Inject
//    ProductViewModel productViewModel;


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
        productByShopList = new ArrayList<>();
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

        //        Set EmptyView ------------------------
        View emptyView = getLayoutInflater().inflate(R.layout.layout_empty_cart_view, (ViewGroup) rvCart.getParent(), false);
        cartAdapter.setEmptyView(R.layout.layout_empty_cart_view, (ViewGroup) rvCart.getParent());


//        Event Checkbox  ----------------------------------
        Disposable disposable = RxBus.getInstance()
                .toObservable(CheckboxCartEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
                            cartProductSectionList.get(event.adapterPosition).setChecked(event.isChecked);
                            for (int i=event.adapterPosition+1; i<cartProductSectionList.size(); i++) {
                                if (cartProductSectionList.get(i).isHeader) break;
                                cartProductSectionList.get(i).setChecked(event.isChecked);
                            }
                            cartAdapter.notifyDataSetChanged();
                        }
                );
        addDisposable(disposable);
    }


    @OnClick(R.id.btnBack)
    public void onBack() {
        onBackPressed();
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

    private int currentPositionShop;
    private ProductSection productSection;
    @Override
    public void showCartProduct(List<Product> productList) {
        if (productList.size() > 0) {

            String currentShopId = productList.get(0).getShop().getId();
            cartProductSectionList.clear();
            productByShopList.clear();
            currentPositionShop = 0;
            productSection = new ProductSection(true, productList.get(0).getShop().getName());
            cartProductSectionList.add(productSection);
            while (productList.size() > 0) {
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getShop().getId().equals(currentShopId)) {
                        productSection = new ProductSection(productList.get(i), currentPositionShop);
                        cartProductSectionList.add(productSection);
                        productList.remove(i);
                        i--;
                    }
                }
                cartProductSectionList.get(cartProductSectionList.size()-1).setEnd(true);
                if (productList.size() > 0) {
                    currentShopId = productList.get(0).getShop().getId();
                    cartProductSectionList.add(new ProductSection(true, productList.get(0).getShop().getName()));

                }
            }

            cartAdapter.notifyDataSetChanged();
        }
    }
}
