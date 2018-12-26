package com.gtv.hanhee.testlayout.dagger2.module;

import android.app.Application;
import android.content.Context;

import com.gtv.hanhee.testlayout.api.BiboService;
import com.gtv.hanhee.testlayout.api.StorageService;
import com.gtv.hanhee.testlayout.dagger2.ApplicationContext;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application mApplication;
    private Context context;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    BiboService provideBiboService() {
        return BiboService.Factory.makeBiboService(mApplication);
    }

    @Provides
    @Singleton
    StorageService provideStorageService() {
        return StorageService.Factory.makeStorageService(mApplication);
    }
}
