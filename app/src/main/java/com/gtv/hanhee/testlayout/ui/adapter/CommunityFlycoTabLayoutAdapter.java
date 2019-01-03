package com.gtv.hanhee.testlayout.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class CommunityFlycoTabLayoutAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;
    String[] mDatas;

    public CommunityFlycoTabLayoutAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] mDatas) {
        super(fm);
        this.fragmentList = fragmentList;
        this.mDatas = mDatas;

    }


    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas[position];
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}


