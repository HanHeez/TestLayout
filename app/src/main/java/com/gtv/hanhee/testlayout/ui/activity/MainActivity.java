package com.gtv.hanhee.testlayout.ui.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.gtv.hanhee.testlayout.base.BaseActivity;
import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.ui.fragment.ShopCategoryFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ShopFragment;
import com.gtv.hanhee.testlayout.ui.fragment.ShopHomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initDatas() {

    }
    Fragment fragment;
    @Override
    protected void configViews() {
            fragment = new ShopFragment();
            Bundle args = new Bundle();         ;
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment, fragment)
                    .commit();
    }
}
