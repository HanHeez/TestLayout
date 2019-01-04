package com.gtv.hanhee.testlayout.api;

import com.gtv.hanhee.testlayout.dagger2.PreferencesHelper;

import javax.inject.Inject;

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
