package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.gtv.hanhee.testlayout.utils.SharedPreferencesUtil;
import com.gtv.hanhee.testlayout.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity implements OrderContract.View, OnItemRvClickListener<Object> {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rvProduct)
    RecyclerView rvProduct;
    @BindView(R.id.txtTotalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.txtUsername)
    TextView txtUsername;
    @BindView(R.id.txtPhoneNumber)
    TextView txtPhoneNumber;
    @BindView(R.id.btnEditUserInfo)
    TextView btnEditUserInfo;
    @BindView(R.id.rlAddUserInfo)
    RelativeLayout rlAddUserInfo;
    @BindView(R.id.rlUserInfo)
    RelativeLayout rlUserInfo;

    @Inject
    OrderPresenter mPresenter;
    private OrderProductAdapter orderProductAdapter;
    private List<Product> productList;
    private List<AddressInfo> addressInfoList;
    private AddressInfo addressInfo;
    private List<ProductSection> productSectionList;
    private static final String GET_TYPE = "type";
    private static final String PRODUCT_ID = "productId";
    private static final String AMOUNT_BUY_NOW = "amountBuyNow";
    private String type="";
    private boolean isHaveAddress = false;
    private int addressPosition;
    private String productId="";
    private Product product;
    private int amountBuyNow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    public static void startActivity(Context context, String type, String productId, int amount) {
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(GET_TYPE, type);
        intent.putExtra(AMOUNT_BUY_NOW, amount);
        intent.putExtra(PRODUCT_ID, productId);
        context.startActivity(intent);
    }

    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            type = savedInstanceState.getString(GET_TYPE, "");
            productId = savedInstanceState.getString(PRODUCT_ID, "");
            amountBuyNow = savedInstanceState.getInt(AMOUNT_BUY_NOW, 0);
        } else {
            type = getIntent().getStringExtra(GET_TYPE);
            productId = getIntent().getStringExtra(PRODUCT_ID);
            amountBuyNow = getIntent().getIntExtra(AMOUNT_BUY_NOW, 0);
        }
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
        if (type.equals("buynow")) {
            mPresenter.getProduct(token, productId);
        } else {
            mPresenter.getCartProduct();
        }
        mPresenter.getListAddressInfo(token);

//        shopPresenter.getCartProduct();
    }

    @OnClick(R.id.btnBack)
    public void onBack(){
        onBackPressed();
    }


    @OnClick(R.id.btnEditUserInfo)
    public void onEditUserInfo(){
       if (isHaveAddress) {
           Intent intent = new Intent(this, UserInfoChooseActivity.class);
           startActivity(intent);
       } else {
           //TODO: add address
           Toast.makeText(mContext, "Thêm địa chỉ", Toast.LENGTH_SHORT).show();
       }
    }
    @OnClick(R.id.rlAddUserInfo)
    public void onAddUserInfo(){
        //TODO: add address 2
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
                if (type.equals("buynow")) {
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
        rvProduct.setNestedScrollingEnabled(false);
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
        product.setOrderAmount(amountBuyNow);
        productList.clear();
        productSectionList.clear();
        productList.add(product);
        addProductToList();
    }

    private int currentPositionShop = 0;
    private ProductSection productSection;
    private long shopProductTotalPrice = 0;
    private int orderShopTotalProduct = 0;
    private void addProductToList() {
        if (productList.size() > 0) {
            String currentShopId = productList.get(0).getShop().getId();
            productSectionList.clear();
            currentPositionShop = 0;
            shopProductTotalPrice = 0;
            orderShopTotalProduct = 0;
            productSection = new ProductSection(true, productList.get(0).getShop().getName());
            productSectionList.add(productSection);
            while (productList.size() > 0) {
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getShop().getId().equals(currentShopId)) {
                        orderShopTotalProduct++;
                        long productTruePrice = productList.get(i).getPrice()*(100-productList.get(i).getDiscountPercent())*productList.get(i).getOrderAmount()/100;
                        shopProductTotalPrice = shopProductTotalPrice+ productTruePrice;
                        productList.get(i).setOrderTotalPrice(shopProductTotalPrice);
                        productList.get(i).setOrderShopTotalProduct(orderShopTotalProduct);
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
                    shopProductTotalPrice = 0;
                    orderShopTotalProduct = 0;
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
                    long discountPrice = productSectionList.get(i).t.getPrice() * (100 - productSectionList.get(i).t.getDiscountPercent()) / 100;
                    totalPrice = totalPrice + discountPrice * productSectionList.get(i).t.getOrderAmount();

            }
        }
        txtTotalPrice.setText(StringUtils.formatPrice(totalPrice + ""));
    }


    @Override
    public void showCartProduct(List<Product> productListResult) {
        productList.clear();
        productSectionList.clear();
        for (int i=0;i<productListResult.size();i++) {
            if (productListResult.get(i).isCheckedProduct()){
                productList.add(productListResult.get(i));
            }
        }
        addProductToList();
    }

    @Override
    public void showListAddressInfo(List<AddressInfo> addressInfoListResult) {
        addressPosition = SharedPreferencesUtil.getInstance().getInt("addressUser", 0);
        if (addressInfoListResult.size()>0) {
            addressInfo = addressInfoListResult.get(addressPosition);
            rlAddUserInfo.setVisibility(View.GONE);
            rlUserInfo.setVisibility(View.VISIBLE);
            txtAddress.setText(addressInfo.getAddress());
            txtUsername.setText(addressInfo.getFullName());
            isHaveAddress = true;
            txtPhoneNumber.setText(addressInfo.getPhoneNumber());
        } else {
            isHaveAddress = false;
            rlAddUserInfo.setVisibility(View.VISIBLE);
            rlUserInfo.setVisibility(View.GONE);
        }
    }

    @Override
    public void successSendAddressInfo(String message) {

    }
}


