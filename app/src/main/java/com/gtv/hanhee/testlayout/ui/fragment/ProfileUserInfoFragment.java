package com.gtv.hanhee.testlayout.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseFragment;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.GroupDetails;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.SubCategory;
import com.gtv.hanhee.testlayout.model.User;
import com.gtv.hanhee.testlayout.ui.activity.ProductDetailActivity;
import com.gtv.hanhee.testlayout.ui.adapter.ProfileUserJoinGroupAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopHomeGridAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopHomeRowAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.ShopSubCategoryAdapter;
import com.gtv.hanhee.testlayout.ui.contract.ProfileUserInfoContract;
import com.gtv.hanhee.testlayout.ui.customview.CustomFragmentHeader;
import com.gtv.hanhee.testlayout.ui.presenter.ProfileUserInfoPresenter;
import com.gtv.hanhee.testlayout.ui.presenter.ProfileUserPresenter;
import com.gtv.hanhee.testlayout.ui.presenter.ShopCategoryPresenter;
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
public class ProfileUserInfoFragment extends BaseFragment implements ProfileUserInfoContract.View, OnItemRvClickListener<Product> {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.txtCity)
    TextView txtCity;
    @BindView(R.id.txtAsterism)
    TextView txtAsterism;
    @BindView(R.id.txtEmail)
    TextView txtEmail;
    @BindView(R.id.txtJoinGroupNoData)
    TextView txtJoinGroupNoData;
    @BindView(R.id.rvJoinGroup)
    RecyclerView rvJoinGroup;

    private ProfileUserJoinGroupAdapter mAdapter;

    private List<GroupDetails> groupDetailsList;

    @Inject
    ProfileUserInfoPresenter mPresenter;

    private String userId;
    private static final String USER_ID = "userID";

    public static Fragment newInstance(String userID) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_ID, userID);
        Fragment fragment = new ProfileUserInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initDataGetFromArgument(Bundle savedInstanceState) {
        super.initDataGetFromArgument(savedInstanceState);
        if (savedInstanceState != null) {
            userId = savedInstanceState.getString(USER_ID);
        } else {
            userId = getArguments().getString(USER_ID);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_profile_user_info;
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
        mPresenter.getUserInfoById(token, userId);
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
        groupDetailsList = new ArrayList<>();

        mAdapter = new ProfileUserJoinGroupAdapter(activity, groupDetailsList, this);
        rvJoinGroup.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvJoinGroup.setLayoutManager(linearLayoutManager);
        rvJoinGroup.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {

        });


    }

    @Override
    public void complete() {

    }

    @Override
    public void onItemRvClick(View view, Product item, int adapterPosition) {

    }

    @Override
    public void showUserInfoByid(User userResult) {


//        get data more  --------------------------

        //        Close loading screen  ------------------
        isErrorData = false;
        if (skeletonScreen != null) {
            skeletonScreen.hide();
        }

    }

    @Override
    public void showUserInfoMom(User userResult) {

    }
}

