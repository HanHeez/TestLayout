package com.gtv.hanhee.testlayout.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Message;
import com.gtv.hanhee.testlayout.ui.adapter.AddressInfoAdapter;
import com.gtv.hanhee.testlayout.ui.contract.UserInfoChooseContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.customview.FlipVerticalSwingEnterDialog.BaseAnimatorSet;
import com.gtv.hanhee.testlayout.ui.customview.animationstyle.FadeExit;
import com.gtv.hanhee.testlayout.ui.customview.animationstyle.FallEnter;
import com.gtv.hanhee.testlayout.ui.customview.animationstyle.SlideBottomExit;
import com.gtv.hanhee.testlayout.ui.customview.dialog.NormalDialog;
import com.gtv.hanhee.testlayout.ui.customview.dialog.OnBtnClickL;
import com.gtv.hanhee.testlayout.ui.presenter.UserInfoChoosePresenter;
import com.gtv.hanhee.testlayout.utils.SharedPreferencesUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class UserInfoChooseActivity extends BaseActivity implements UserInfoChooseContract.View, OnItemRvClickListener<Object> {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rvAddress)
    RecyclerView rvAddress;
    @BindView(R.id.btnAddUserInfo)
    LinearLayout btnAddUserInfo;
    @BindView(R.id.rootView)
    RelativeLayout rootView;


    @Inject
    UserInfoChoosePresenter mPresenter;
    private AddressInfoAdapter addressInfoAdapter;

    private List<AddressInfo> addressInfoList;
    AddressInfo addressInfo;
    private static final String TOKEN_USER = "tokenUser";
    private String tokenUser;
    private int addressPosition = 0;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private String addressDefaultId = "";

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


    @OnClick(R.id.btnBack)
    public void onBack() {
        onBackPressed();
    }

    @OnClick(R.id.btnAddUserInfo)
    public void addUserInfo() {
        UserInfoAddActivity.startActivity(this, "");
    }


    //    show loading screen ---------
    @Override
    public void initDatas() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    onRefreshing data ------------

    @Override
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        mPresenter.getListAddressInfo(token);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            SharedPreferencesUtil.getInstance().putString("addressDefaultId", addressInfoList.get(position).getId());
            finish();
        });
    }


    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {
        if (item instanceof AddressInfo) {
            AddressInfo addressInfo = (AddressInfo) item;
            switch (view.getId()) {
                case R.id.btnDelete:
                    showAlertDelete(addressInfo.getAddress(), addressInfo.getId(), addressInfo.isDefault());
                    break;
                case R.id.btnEdit:
                    UserInfoAddActivity.startActivity(this, addressInfo.getId());
                    break;
            }
        }
    }

    private void showAlertDelete(String address, String addressId, Boolean isDefaultAddress) {
        mBasIn = new FallEnter();
        mBasOut = new FadeExit();
        final NormalDialog dialog = new NormalDialog(mContext);
        dialog.setmBtnRightText("Xóa");
        dialog.content("Bạn có muốn xóa địa chỉ này? \n" + address)//
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
                        if (isDefaultAddress) {
                            SharedPreferencesUtil.getInstance().putString("addressDefaultId", "");
                        }
                        mPresenter.removeAddressInfo(token, addressId);
                        dialog.dismiss();
                    }
                });
    }

    @Override
    public void complete() {
    }

    @Override
    public void showListAddressInfo(List<AddressInfo> addressInfoListResult) {
        addressInfoList.clear();
        addressInfoList.addAll(addressInfoListResult);
        if (addressInfoListResult.size() > 0) {
            addressDefaultId = SharedPreferencesUtil.getInstance().getString("addressDefaultId", "");
            if (addressDefaultId.equals("")) {
                SharedPreferencesUtil.getInstance().putString("addressDefaultId", addressInfoList.get(0).getId());
                addressInfoList.get(0).setDefault(true);
            } else {
                for (int i = 0; i < addressInfoList.size(); i++) {
                    if (addressInfoList.get(i).getId().equals(addressDefaultId)) {
                        AddressInfo addressInfo = addressInfoList.get(i);
                        addressInfo.setDefault(true);
                        addressInfoList.remove(i);
                        addressInfoList.add(0, addressInfo);
                        break;
                    }
                }
            }
        }
        addressInfoAdapter.notifyDataSetChanged();
        //        Close loading screen ------------------
        isErrorData = false;
        if (skeletonScreen!=null) {
            skeletonScreen.hide();
        }
    }

    @Override
    public void successRemoveAddressInfo(Message message) {
        mPresenter.getListAddressInfo(token);
    }


}


