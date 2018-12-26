package com.gtv.hanhee.testlayout.base;

import android.app.Application;
import android.content.Context;

import com.gtv.hanhee.testlayout.dagger2.component.ApplicationComponent;
import com.gtv.hanhee.testlayout.dagger2.component.DaggerApplicationComponent;
import com.gtv.hanhee.testlayout.dagger2.module.ApplicationModule;
import com.gtv.hanhee.testlayout.utils.SharedPreferencesUtil;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by Hoang Nam on 06/02/2017.
 */
public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        initComponent();
        initPrefs();

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
