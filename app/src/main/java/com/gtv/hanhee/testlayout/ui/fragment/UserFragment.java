package com.gtv.hanhee.testlayout.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseFragment;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.CategoryService;
import com.gtv.hanhee.testlayout.model.UserSetting;
import com.gtv.hanhee.testlayout.ui.adapter.UserServiceAdapter;
import com.gtv.hanhee.testlayout.ui.adapter.UserSettingAdapter;
import com.gtv.hanhee.testlayout.ui.contract.UserContract;
import com.gtv.hanhee.testlayout.ui.presenter.UserPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment implements UserContract.View, OnItemRvClickListener<Object> {

    @BindView(R.id.rvSetting)
    RecyclerView rvSetting;
    @BindView(R.id.rvService)
    RecyclerView rvService;
    @BindView(R.id.rootView)
    LinearLayout rootView;

    private UserSettingAdapter userSettingAdapter;
    private UserServiceAdapter userServiceAdapter;

    @Inject
    UserPresenter mPresenter;

    private String userId="";
    private static final String USER_ID = "userID";
    private List<UserSetting> settingList;
    private List<CategoryService> serviceList;

    public static Fragment newInstance(String userID) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_ID, userID);
        Fragment fragment = new UserFragment();
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
        return R.layout.fragment_user;
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
        mPresenter.getUserSettingList(token);
    }

    @Override
    public void configViews() {

//        Setting RecyclerView ----------------
        settingList = new ArrayList<>();
        serviceList = new ArrayList<>();


//        Rv Setting -----------------
        userSettingAdapter = new UserSettingAdapter(activity, settingList, this);
        rvSetting.setHasFixedSize(true);
        rvSetting.setNestedScrollingEnabled(false);
        GridLayoutManager settingLayoutManager = new GridLayoutManager(activity, 4);
        rvSetting.setLayoutManager(settingLayoutManager);
        rvSetting.setAdapter(userSettingAdapter);

//        RV Service -------------
        userServiceAdapter = new UserServiceAdapter(activity, serviceList, this);
        rvService.setHasFixedSize(true);
        rvService.setNestedScrollingEnabled(false);
        GridLayoutManager serviceLayoutManager = new GridLayoutManager(activity, 4);
        rvService.setLayoutManager(serviceLayoutManager);
        rvService.setAdapter(userServiceAdapter);
    }

    @Override
    public void complete() {

    }

    @Override
    public void showUserSettingList(List<UserSetting> userSettingListResult) {
        settingList.clear();
        settingList.addAll(userSettingListResult);
        userSettingAdapter.notifyDataSetChanged();
        mPresenter.getUserServiceList(token);
    }

    @Override
    public void showUserServiceList(List<CategoryService> userServiceListResult) {
        serviceList.clear();
        serviceList.addAll(userServiceListResult);
        userServiceAdapter.notifyDataSetChanged();

        //        Close loading screen ------------------
        isErrorData = false;
        if (skeletonScreen != null) {
            skeletonScreen.hide();
        }
    }

    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }
}




