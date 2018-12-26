package com.gtv.hanhee.testlayout.dagger2.module;

import android.content.Context;

import com.gtv.hanhee.testlayout.dagger2.ActivityContext;


import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Context mContext;

    public ActivityModule(Context context) {
        context = context;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mContext;
    }

}
