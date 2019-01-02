package com.gtv.hanhee.testlayout.base;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.gtv.hanhee.testlayout.dagger2.component.ApplicationComponent;


import com.gtv.hanhee.testlayout.dagger2.component.DaggerApplicationComponent;
import com.gtv.hanhee.testlayout.dagger2.module.ApplicationModule;
import com.gtv.hanhee.testlayout.model.dao.ProductDao;
import com.gtv.hanhee.testlayout.model.database.AppDatabase;
import com.gtv.hanhee.testlayout.utils.SharedPreferencesUtil;
import com.squareup.leakcanary.LeakCanary;



/**
 * Created by Hoang Nam on 06/02/2017.
 */
public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private AppDatabase db;
    public static ProductDao mProductDao;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        initComponent();
        initPrefs();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "product-store").build();
        mProductDao = db.productDao();
    }

    private void initComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), Config.SHARED_PREF, 0);
    }

}
