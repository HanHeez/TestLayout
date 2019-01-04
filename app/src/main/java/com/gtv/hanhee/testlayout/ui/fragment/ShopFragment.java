package com.gtv.hanhee.testlayout.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.base.BaseFragment;
import com.gtv.hanhee.testlayout.base.OnItemRvClickListener;
import com.gtv.hanhee.testlayout.model.CategoriesNews;
import com.gtv.hanhee.testlayout.model.Category;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.adapter.CommunityFlycoTabLayoutAdapter;
import com.gtv.hanhee.testlayout.ui.contract.ShopContract;
import com.gtv.hanhee.testlayout.ui.contract.ShopHomeContract;
import com.gtv.hanhee.testlayout.ui.presenter.ShopPresenter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ShopFragment extends BaseFragment implements ShopContract.View {

    @BindView(R.id.tabLayout)
    SmartTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private CommunityFlycoTabLayoutAdapter communityFlycoTabLayoutAdapter;
    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    @Inject
    ShopPresenter shopPresenter;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_shop;
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

