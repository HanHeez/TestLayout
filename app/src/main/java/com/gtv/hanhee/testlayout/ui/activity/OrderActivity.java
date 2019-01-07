package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ProductSection;
import com.gtv.hanhee.testlayout.ui.adapter.OrderProductAdapter;
import com.gtv.hanhee.testlayout.ui.contract.OrderContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.presenter.OrderPresenter;
import com.gtv.hanhee.testlayout.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class OrderActivity extends BaseActivity implements OrderContract.View, OnItemRvClickListener<Object> {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rvProduct)
    RecyclerView rvProduct;
    @BindView(R.id.txtTotalPrice)
    TextView txtTotalPrice;

    @Inject
    OrderPresenter mPresenter;
    private OrderProductAdapter orderProductAdapter;
    private List<Product> productList;
    private List<ProductSection> productSectionList;
    private static final String GET_TYPE = "type";
    private static final String PRODUCT_ID = "productId";
    private String type="";
    private String productId="";
    private Product product;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    public static void startActivity(Context context, String type, String productId) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(GET_TYPE, type);
        intent.putExtra(PRODUCT_ID, productId);
        context.startActivity(intent);
    }




    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            type = savedInstanceState.getString(GET_TYPE);
            productId = savedInstanceState.getString(PRODUCT_ID);
        } else {
            type = getIntent().getStringExtra(GET_TYPE);
            productId = savedInstanceState.getString(PRODUCT_ID);
        }
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
        if (type=="buynow") {
            mPresenter.getProduct(token, productId);
        } else {
            mPresenter.getCartProduct();
        }

//        shopPresenter.getCartProduct();
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
        refreshLayout.setRefreshHeader(new CustomFragmentHeader(mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                if (type=="buynow") {
                    mPresenter.getProduct(token, productId);
                } else {
                    mPresenter.getCartProduct();
                }
            }
        });

//        Setting Recycler View ---------------
        productList = new ArrayList<>();
        productSectionList = new ArrayList<>();
        orderProductAdapter = new OrderProductAdapter(this, productSectionList, this);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(this);
        rvProduct.setHasFixedSize(true);
        rvProduct.setLayoutManager(layoutManagerNews);
        rvProduct.setAdapter(orderProductAdapter);

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
    public void showProduct(Product productResult) {
        product = productResult;
        productList.clear();
        productList.add(product);
        addProductToList();

    }

    private int currentPositionShop = 0;
    private ProductSection productSection;

    private void addProductToList() {
        if (productList.size() > 0) {
            String currentShopId = productList.get(0).getShop().getId();
            productSectionList.clear();
            currentPositionShop = 0;
            productSection = new ProductSection(true, productList.get(0).getShop().getName());
            productSectionList.add(productSection);
            while (productList.size() > 0) {
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getShop().getId().equals(currentShopId)) {
                        productSection = new ProductSection(productList.get(i), currentPositionShop);
                        productSectionList.add(productSection);
                        if (!productSection.t.isCheckedProduct()) {
                            productSectionList.get(currentPositionShop).setCheckedShop(false);
                        }
                        productList.remove(i);
                        i--;
                    }
                }
                productSectionList.get(productSectionList.size() - 1).setEnd(true);
                if (productList.size() > 0) {
                    currentShopId = productList.get(0).getShop().getId();
                    currentPositionShop = productSectionList.size();
                    productSectionList.add(new ProductSection(true, productList.get(0).getShop().getName()));

                }
            }
            calculateTotalPrice();
            orderProductAdapter.notifyDataSetChanged();
        }

    }
    private long totalPrice = 0;
    public void calculateTotalPrice() {
        totalPrice = 0;
        for (int i = 0; i < productSectionList.size(); i++) {
            if (!productSectionList.get(i).isHeader) {
                if (productSectionList.get(i).t.isCheckedProduct()) {
                    long discountPrice = productSectionList.get(i).t.getPrice() * (100 - productSectionList.get(i).t.getDiscountPercent()) / 100;
                    totalPrice = totalPrice + discountPrice * productSectionList.get(i).t.getOrderAmount();
                }
            }
        }
        txtTotalPrice.setText(StringUtils.formatPrice(totalPrice + ""));
    }


    @Override
    public void showCartProduct(List<Product> productListResult) {

    }

    @Override
    public void showAddressInfoList(List<AddressInfo> addressInfoListResult) {

    }

    @Override
    public void successSendAddressInfo(String message) {

    }
}


