package com.gtv.hanhee.testlayout.api;

import com.gtv.hanhee.testlayout.dagger2.PreferencesHelper;
import com.gtv.hanhee.testlayout.model.BannerDetail;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class StorageManager {

    private final StorageService service;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public StorageManager(StorageService storageService,
                          PreferencesHelper preferencesHelper) {
        service = storageService;
        mPreferencesHelper = preferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }


}
