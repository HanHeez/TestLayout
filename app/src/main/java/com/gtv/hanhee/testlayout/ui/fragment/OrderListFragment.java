package com.gtv.hanhee.testlayout.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseFragment;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.GroupDetails;
import com.gtv.hanhee.testlayout.model.Order;
import com.gtv.hanhee.testlayout.model.OrderSection;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ProductSection;
import com.gtv.hanhee.testlayout.model.User;
import com.gtv.hanhee.testlayout.ui.activity.CartActivity;
import com.gtv.hanhee.testlayout.ui.activity.ProductDetailActivity;
import com.gtv.hanhee.testlayout.ui.adapter.CartAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.OrderListAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ProfileUserJoinGroupAdapter;
import com.gtv.hanhee.testlayout.ui.contract.OrderListContract;
import com.gtv.hanhee.testlayout.ui.contract.ProfileUserInfoContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.presenter.OrderListPresenter;
import com.gtv.hanhee.testlayout.ui.presenter.ProfileUserInfoPresenter;
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
public class OrderListFragment extends BaseFragment implements OrderListContract.View, OnItemRvClickListener<Product> {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.rvOrder)
    RecyclerView rvOrder;
    @BindView(R.id.rvRecommend)
    RecyclerView rvRecommend;

    private OrderListAdapter mAdapter;

    private List<Order> orderList;
    private List<OrderSection> orderSectionList;

    @Inject
    OrderListPresenter mPresenter;

    private String getType;
    private static final String GET_TYPE = "getType";

    public static Fragment newInstance(String getType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(GET_TYPE, getType);
        Fragment fragment = new OrderListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initDataGetFromArgument(Bundle savedInstanceState) {
        super.initDataGetFromArgument(savedInstanceState);
        if (savedInstanceState != null) {
            getType = savedInstanceState.getString(GET_TYPE);
        } else {
            getType = getArguments().getString(GET_TYPE);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void attachView() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
    }


    //    show loading screen ---------
    @Override
    public void initDatas() {
        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    show error screen -------------
    @Override
    public void showError() {
        isErrorData = true;
        showErrorScreen(rootView);
    }

    //    click loading screen -----------
    @Override
    public void onSkeletonViewClick(View view) {
        switch (view.getId()) {
            case R.id.page_tip_eventview:
                onRefreshing();
                break;
        }
    }

    //    onRefreshing data ------------
    private void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
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
                onRefreshing();
                ;
            }
        });
//        Setting RecyclerView ----------------
        orderList = new ArrayList<>();
        orderSectionList = new ArrayList<>();
        mAdapter = new OrderListAdapter(getActivity(), orderSectionList, this);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(getActivity());
        rvOrder.setHasFixedSize(true);
        rvOrder.setNestedScrollingEnabled(false);
        rvOrder.setLayoutManager(layoutManagerNews);
        rvOrder.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

        });



    }

    @Override
    public void complete() {

    }

    @Override
    public void onItemRvClick(View view, Product item, int adapterPosition) {

    }
}



