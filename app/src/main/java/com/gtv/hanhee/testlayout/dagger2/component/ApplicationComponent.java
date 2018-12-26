package com.gtv.hanhee.testlayout.dagger2.component;

import android.app.Application;
import android.content.Context;


import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.api.BiboService;
import com.gtv.hanhee.testlayout.api.StorageService;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.dagger2.ApplicationContext;
import com.gtv.hanhee.testlayout.dagger2.PreferencesHelper;
import com.gtv.hanhee.testlayout.dagger2.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MyApplication myApplication);

    @ApplicationContext
    Context getContext();

    Application application();

    BiboService getBiboService();
    StorageService getStorageService();
    PreferencesHelper getPreferencesHelper();
    BiboManager getBiboManager();
}
