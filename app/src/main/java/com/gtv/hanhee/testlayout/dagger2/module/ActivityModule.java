package com.gtv.hanhee.testlayout.dagger2.module;

import android.content.Context;

import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.dagger2.ActivityContext;
import com.gtv.hanhee.testlayout.model.dao.ProductDao;
import com.gtv.hanhee.testlayout.model.dao.ProductDao_Impl;
import com.gtv.hanhee.testlayout.model.database.AppDatabase;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Context mContext;

    public ActivityModule(Context context) {
        context = context;
    }


    @Provides
    @Named("ProductDao")
    ProductDao provideInfoDao(){
        return new ProductDao_Impl(AppDatabase.getInstance(mContext));
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mContext;
    }

}
