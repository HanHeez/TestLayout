package com.gtv.hanhee.testlayout.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseFragment;
import com.gtv.hanhee.testlayout.model.Category;
import com.gtv.hanhee.testlayout.ui.activity.CartActivity;
import com.gtv.hanhee.testlayout.ui.activity.ProfileUserActivity;
import com.gtv.hanhee.testlayout.ui.activity.ShopSearchActivity;
import com.gtv.hanhee.testlayout.ui.adapter.CommunityFlycoTabLayoutAdapter;
import com.gtv.hanhee.testlayout.ui.contract.ShopContract;
import com.gtv.hanhee.testlayout.ui.presenter.ShopPresenter;
import com.gtv.hanhee.testlayout.utils.SharedPreferencesUtil;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopFragment extends BaseFragment implements ShopContract.View {

    @BindView(R.id.tabLayout)
    SmartTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.txtAmountCart)
    TextView txtAmountCart;

    private CommunityFlycoTabLayoutAdapter communityFlycoTabLayoutAdapter;
    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    @Inject
    ShopPresenter shopPresenter;
    private int amountProductCart = 0;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_shop;
    }

    @OnClick(R.id.rlSearch)
    public void onSearch() {
        Intent intent = new Intent(activity, ShopSearchActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnMessage)
    public void goToUser() {
        Intent intent = new Intent(activity, ProfileUserActivity.class);
        startActivity(intent);
    }

    @Override
    public void attachView() {
        activityComponent().inject(this);
        shopPresenter.attachView(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (shopPresenter !=null) {
            shopPresenter.detachView();
        }
    }

    @Override
    public void initDatas() {
        shopPresenter.getListCategory(token);
    }

    @Override
    public void configViews() {
    }

    public void settingAmountCart() {
        if (amountProductCart == 0) {
            txtAmountCart.setVisibility(View.GONE);
        } else {
            txtAmountCart.setVisibility(View.VISIBLE);
            if(amountProductCart>99) {
                txtAmountCart.setText("99+");
            } else txtAmountCart.setText(amountProductCart+"");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        amountProductCart = SharedPreferencesUtil.getInstance().getInt("amountCart", 0);
        settingAmountCart();
    }

    @OnClick(R.id.btnCart)
    public void goToCart() {
        Intent intent = new Intent(activity, CartActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.imgCart)
    public void goToImgCart() {
        Intent intent = new Intent(activity, CartActivity.class);
        startActivity(intent);
    }
    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showListCategory(List<Category> categoryListResult) {
        mFragments.clear();
        mTitles.clear();
        mTitles.add("Trang chủ");
        mFragments.add(new ShopHomeFragment());

        for (int i = 0; i < categoryListResult.size()-1; i++) {
            mTitles.add(categoryListResult.get(i).getName());
            mFragments.add(ShopCategoryFragment.newInstance(categoryListResult.get(i).getId()));
        }

        communityFlycoTabLayoutAdapter = new CommunityFlycoTabLayoutAdapter(getChildFragmentManager(), mFragments, mTitles.toArray(new String[mTitles.size()]) );
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(communityFlycoTabLayoutAdapter);
        tabLayout.setViewPager(viewPager);
    }
}

