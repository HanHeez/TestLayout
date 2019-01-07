package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.manager.ChangeProductAmountEvent;
import com.gtv.hanhee.testlayout.manager.CheckboxCartEvent;
import com.gtv.hanhee.testlayout.manager.CheckboxProductEvent;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ProductSection;
import com.gtv.hanhee.testlayout.ui.adapter.CartAdapter;
import com.gtv.hanhee.testlayout.ui.contract.CartContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.customview.FlipVerticalSwingEnterDialog.BaseAnimatorSet;
import com.gtv.hanhee.testlayout.ui.customview.animationstyle.FallEnter;
import com.gtv.hanhee.testlayout.ui.customview.animationstyle.SlideBottomExit;
import com.gtv.hanhee.testlayout.ui.customview.dialog.NormalDialog;
import com.gtv.hanhee.testlayout.ui.customview.dialog.OnBtnClickL;
import com.gtv.hanhee.testlayout.ui.presenter.CartPresenter;
import com.gtv.hanhee.testlayout.utils.RxBus;
import com.gtv.hanhee.testlayout.utils.SharedPreferencesUtil;
import com.gtv.hanhee.testlayout.utils.StringUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CartActivity extends BaseActivity implements CartContract.View, OnItemRvClickListener<Object> {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rvCart)
    RecyclerView rvCart;
    @BindView(R.id.rvRecommend)
    RecyclerView rvRecommend;
    @BindView(R.id.cbAll)
    CheckBox cbAll;
    @BindView(R.id.txtTotalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.btnDelete)
    TextView btnDelete;
    @BindView(R.id.btnFinish)
    TextView btnFinish;
    @BindView(R.id.btnSubmitDelete)
    TextView btnSubmitDelete;
    @BindView(R.id.btnSubmit)
    LinearLayout btnSubmit;
    @BindView(R.id.rlTotalPrice)
    RelativeLayout rlTotalPrice;
    @BindView(R.id.lnDelete)
    LinearLayout lnDelete;
    @BindView(R.id.lnBottomBar)
    LinearLayout lnBottomBar;
    @BindView(R.id.txtTip)
    TextView txtTip;
    private Button btnCartGoHome;
    private View emptyView;

    @Inject
    CartPresenter cartPresenter;
    private CartAdapter cartAdapter;
    private List<Product> cartProductList;
    private List<ProductSection> cartProductSectionList;
    private static final String PRODUCT_ID = "productId";
    private String productId = "";
    private long totalPrice = 0;
    private int amountProductCart = 0;

    BaseAnimatorSet mBasIn;
    BaseAnimatorSet mBasOut;

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
        showTip = false;
        cartPresenter.getCartProduct();
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
                cartPresenter.getCartProduct();
            }
        });
//        Setting Recycler View ---------------
        cartProductList = new ArrayList<>();
        cartProductSectionList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, cartProductSectionList, this);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(this);
        rvCart.setHasFixedSize(true);
        rvCart.setNestedScrollingEnabled(false);
        rvCart.setLayoutManager(layoutManagerNews);
        rvCart.setAdapter(cartAdapter);
        cartAdapter.setOnItemClickListener((adapter, view, position) -> {
            Log.d("kiemtra", "onclick");
            if (cartProductSectionList.get(position).isHeader) {
            } else {
//                ProductDetailActivity.startActivity(CartActivity.this, cartProductSectionList.get(position).t.getId());
                Intent intent = new Intent(CartActivity.this, ProductDetailActivity.class);
                intent.putExtra("productId", cartProductSectionList.get(position).t.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        //        Set EmptyView ------------------------
        emptyView = getLayoutInflater().inflate(R.layout.layout_empty_cart_view, (ViewGroup) rvCart.getParent(), false);
        cartAdapter.setEmptyView(emptyView);
        btnCartGoHome = emptyView.findViewById(R.id.btnCartGoHome);
        btnCartGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

//        Event Checkbox  ----------------------------------
        Disposable disposable = RxBus.getInstance()
                .toObservable(CheckboxCartEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
                            cartProductSectionList.get(event.adapterPosition).setCheckedShop(event.isChecked);
                            for (int i = event.adapterPosition + 1; i < cartProductSectionList.size(); i++) {
                                if (cartProductSectionList.get(i).isHeader) break;
                                cartProductSectionList.get(i).t.setCheckedProduct(event.isChecked);
                                int finalI = i;
                                Completable.fromAction(() -> MyApplication.mProductDao.updateProducts(cartProductSectionList.get(finalI).t)).subscribeOn(Schedulers.io())
                                        .subscribe();
                            }
                            calculateTotalPrice();
                            checkAllProduct();
                            cartAdapter.notifyDataSetChanged();

                        }
                );
        addDisposable(disposable);

        Disposable disposable1 = RxBus.getInstance()
                .toObservable(CheckboxProductEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
                            cartProductSectionList.get(event.adapterPosition).t.setCheckedProduct(event.isChecked);
                            Completable.fromAction(() -> MyApplication.mProductDao.updateProducts(cartProductSectionList.get(event.adapterPosition).t)).subscribeOn(Schedulers.io())
                                    .subscribe();
                            calculateTotalPrice();
                            int positionShop = cartProductSectionList.get(event.adapterPosition).getPositionShop();

                            if (!event.isChecked) {
                                cartProductSectionList.get(positionShop).setCheckedShop(false);
                                cartAdapter.notifyDataSetChanged();
                            } else {
                                boolean checkedShop = true;
                                for (int i = positionShop + 1; i < cartProductSectionList.size(); i++) {
                                    if (!cartProductSectionList.get(i).isHeader) {
                                        if (!cartProductSectionList.get(i).t.isCheckedProduct()) {
                                            checkedShop = false;
                                            break;
                                        }
                                    } else break;
                                }
                                cartProductSectionList.get(positionShop).setCheckedShop(checkedShop);
                            }
                            checkAllProduct();
                            cartAdapter.notifyDataSetChanged();
                        }

                );
        addDisposable(disposable1);

        Disposable disposable3 = RxBus.getInstance()
                .toObservable(ChangeProductAmountEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
                            calculateTotalPrice();
                        }
                );
        addDisposable(disposable3);


        cbAll.setOnClickListener(v -> {
            CheckBox cb = (CheckBox) v;
            for (ProductSection productSection : cartProductSectionList) {
                if (productSection.isHeader) {
                    productSection.setCheckedShop(cb.isChecked());
                } else {
                    productSection.t.setCheckedProduct(cb.isChecked());
                    Completable.fromAction(() -> MyApplication.mProductDao.updateProducts(productSection.t)).subscribeOn(Schedulers.io())
                            .subscribe();
                }
                calculateTotalPrice();
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.btnCheckAll)
    public void setCheckAll() {
        cbAll.performClick();
    }


    public void checkAllProduct() {
        boolean isCheckAll = true;
        for (int i = 0; i < cartProductSectionList.size(); i++) {
            if (!cartProductSectionList.get(i).isHeader) {
                if (!cartProductSectionList.get(i).t.isCheckedProduct()) {
                    isCheckAll = false;
                    break;
                }
            } else {
                if (!cartProductSectionList.get(i).isCheckedShop()) {
                    isCheckAll = false;
                    break;
                }
            }
        }
        cbAll.setChecked(isCheckAll);
    }

    public void calculateTotalPrice() {
        totalPrice = 0;
        for (int i = 0; i < cartProductSectionList.size(); i++) {
            if (!cartProductSectionList.get(i).isHeader) {
                if (cartProductSectionList.get(i).t.isCheckedProduct()) {
                    long discountPrice = cartProductSectionList.get(i).t.getPrice() * (100 - cartProductSectionList.get(i).t.getDiscountPercent()) / 100;
                    totalPrice = totalPrice + discountPrice * cartProductSectionList.get(i).t.getOrderAmount();
                }
            }
        }
        txtTotalPrice.setText(StringUtils.formatPrice(totalPrice + ""));
    }

    @OnClick(R.id.btnSubmitDelete)
    public void onSubmitDelete() {
        boolean isCheckAtLeast = false;
        for (int i = 0; i < cartProductSectionList.size(); i++) {
            if (!cartProductSectionList.get(i).isHeader && cartProductSectionList.get(i).t.isCheckedProduct()) {
                isCheckAtLeast = true;
                break;
            }
        }
        if (isCheckAtLeast) {
            mBasIn = new FallEnter();
            mBasOut = new SlideBottomExit();
            final NormalDialog dialog = new NormalDialog(mContext);
            dialog.setmBtnMiddleText("Xóa");
            dialog.setmBtnRightText("Xóa");
            dialog.content("Bạn có muốn xóa sản phẩm đang chọn ?")//
                    .style(NormalDialog.STYLE_TWO)//
                    .titleTextSize(23)//
                    .showAnim(mBasIn)//
                    .dismissAnim(mBasOut)//
                    .show();

            dialog.setOnBtnClickL(
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            dialog.dismiss();
                        }
                    },
                    new OnBtnClickL() {
                        @Override
                        public void onBtnClick() {
                            deleteCheckedProduct();
                            dialog.dismiss();
                        }
                    });
        } else {
            showTipViewAndDelayClose("Vui lòng chọn ít nhất 1 sản phẩm", txtTip);
        }
    }

    private void deleteCheckedProduct() {
        for (int i = 0; i < cartProductSectionList.size(); i++) {
            ProductSection productSection = cartProductSectionList.get(i);
            if (productSection.isHeader) {
                if (productSection.isCheckedShop()) {
                    cartProductSectionList.remove(i);
                    i--;

                }
            } else {
                if (productSection.t.isCheckedProduct()) {
                    Completable.fromAction(() -> MyApplication.mProductDao.deleteProducts(productSection.t)).subscribeOn(Schedulers.io())
                            .subscribe();
                    amountProductCart = SharedPreferencesUtil.getInstance().getInt("amountCart", 0);
                    amountProductCart = amountProductCart - productSection.t.getOrderAmount();
                    SharedPreferencesUtil.getInstance().putInt("amountCart", amountProductCart);
                    cartProductSectionList.remove(i);
                    i--;
                }
            }
        }
        checkedSizeCart(true);
        calculateTotalPrice();
        cartAdapter.notifyDataSetChanged();
    }

    private void checkedSizeCart(boolean isDelete) {
            if (cartProductSectionList.size() > 0) {
                lnBottomBar.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.VISIBLE);
                if (isDelete) {
                    btnDelete.setVisibility(View.GONE);
                    btnFinish.setVisibility(View.VISIBLE);
                } else {
                    btnDelete.setVisibility(View.VISIBLE);
                    btnFinish.setVisibility(View.GONE);
                }
            } else {
                lnBottomBar.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                btnFinish.setVisibility(View.GONE);
            }
    }

    @OnClick(R.id.btnBack)
    public void onBack() {
        onBackPressed();
    }

    @OnClick(R.id.btnSubmit)
    public void onSubmit() {
        boolean checkProduct = false;
        for (int i=0;i<cartProductSectionList.size();i++) {
            if (!cartProductSectionList.get(i).isHeader && cartProductSectionList.get(i).t.isCheckedProduct()) {
                checkProduct = true;
                break;
            }
        }
        if (checkProduct) {
            OrderActivity.startActivity(this, "cart", "", 0);
        } else {
            showTipViewAndDelayClose("Vui lòng chọn ít nhất 1 sản phẩm", txtTip);
        }
    }


    @OnClick(R.id.btnFinish)
    public void onFinish() {
        btnFinish.setVisibility(View.GONE);
        btnDelete.setVisibility(View.VISIBLE);
        btnSubmit.setVisibility(View.VISIBLE);
        rlTotalPrice.setVisibility(View.VISIBLE);
        lnDelete.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnDelete)
    public void onDelete() {
        btnFinish.setVisibility(View.VISIBLE);
        btnDelete.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);
        rlTotalPrice.setVisibility(View.INVISIBLE);
        lnDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {
        if (item instanceof Product) {
            switch (view.getId()) {
                case R.id.lnCbProduct:
                    CheckBox cbProduct = rvCart.findViewHolderForAdapterPosition(adapterPosition).itemView.findViewById(R.id.cbProduct);
                    cbProduct.performClick();
                    break;
            }
        } else {
            if (item instanceof ProductSection) {
                switch (view.getId()) {
                    case R.id.lnCbShop:
                        CheckBox cbShop = rvCart.findViewHolderForAdapterPosition(adapterPosition).itemView.findViewById(R.id.cbShop);
                        cbShop.performClick();
                        break;
                }
            }
        }
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
            currentPositionShop = 0;
            productSection = new ProductSection(true, productList.get(0).getShop().getName());
            cartProductSectionList.add(productSection);
            while (productList.size() > 0) {
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getShop().getId().equals(currentShopId)) {
                        productSection = new ProductSection(productList.get(i), currentPositionShop);
                        cartProductSectionList.add(productSection);
                        if (!productSection.t.isCheckedProduct()) {
                            cartProductSectionList.get(currentPositionShop).setCheckedShop(false);
                        }
                        productList.remove(i);
                        i--;
                    }
                }
                cartProductSectionList.get(cartProductSectionList.size() - 1).setEnd(true);
                if (productList.size() > 0) {
                    currentShopId = productList.get(0).getShop().getId();
                    currentPositionShop = cartProductSectionList.size();
                    cartProductSectionList.add(new ProductSection(true, productList.get(0).getShop().getName()));

                }
            }
            calculateTotalPrice();
            checkAllProduct();
            cartAdapter.notifyDataSetChanged();
        }
        checkedSizeCart(false);
    }
}
