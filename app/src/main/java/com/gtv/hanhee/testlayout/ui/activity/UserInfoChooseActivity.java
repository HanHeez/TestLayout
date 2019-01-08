package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.ui.adapter.AddressInfoAdapter;
import com.gtv.hanhee.testlayout.ui.contract.UserInfoChooseContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.presenter.UserInfoChoosePresenter;
import com.gtv.hanhee.testlayout.utils.SharedPreferencesUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class UserInfoChooseActivity extends BaseActivity implements UserInfoChooseContract.View, OnItemRvClickListener<Object> {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rvAddress)
    RecyclerView rvAddress;
// 

    @Inject
    UserInfoChoosePresenter mPresenter;
    private AddressInfoAdapter addressInfoAdapter;

    private List<AddressInfo> addressInfoList;
    AddressInfo addressInfo;
    private static final String TOKEN_USER = "tokenUser";
    private String tokenUser;
    private int addressPosition = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info_choose;
    }

    public static void startActivity(Context context, String tokenUser) {
        Intent intent = new Intent(context, UserInfoChooseActivity.class);
        intent.putExtra(TOKEN_USER, tokenUser);
        context.startActivity(intent);
    }


    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            tokenUser = savedInstanceState.getString(TOKEN_USER);
        } else {
            tokenUser = getIntent().getStringExtra(TOKEN_USER);
        }
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getListAddressInfo(token);
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
            }
        });

//        Setting Recycler View ---------------
        addressInfoList = new ArrayList<>();
        addressInfoAdapter = new AddressInfoAdapter(this, addressInfoList, this);
        rvAddress.setHasFixedSize(true);
        rvAddress.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(mContext);
        rvAddress.setLayoutManager(layoutManagerNews);
        rvAddress.setAdapter(addressInfoAdapter);
        addressInfoAdapter.setOnItemClickListener((adapter, view, position) -> {
            //TODO : set click address
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

    @Override
    public void showListAddressInfo(List<AddressInfo> addressInfoListResult) {
        addressPosition = SharedPreferencesUtil.getInstance().getInt("addressUser", 0);
        if (addressInfoListResult.size() > 0) {
            addressInfo = addressInfoListResult.get(addressPosition);
        }
    }
}


