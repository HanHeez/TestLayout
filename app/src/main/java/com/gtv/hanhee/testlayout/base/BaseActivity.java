package com.gtv.hanhee.testlayout.base;//package com.rize.ui.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.api.Constants;
import com.gtv.hanhee.testlayout.dagger2.component.ActivityComponent;
import com.gtv.hanhee.testlayout.dagger2.component.DaggerActivityComponent;
import com.gtv.hanhee.testlayout.dagger2.module.ActivityModule;
import com.gtv.hanhee.testlayout.ui.customview.LoadingDialog;
import com.gtv.hanhee.testlayout.utils.StatusBarMainUtil;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected int statusBarColor = 0;
    protected View statusBarView = null;

    Unbinder unbinder;
    private LoadingDialog dialog;//Dialog loading
    protected String token;

    protected ActivityComponent mActivityComponent;

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(MyApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    protected void startActivity(Class<? extends AppCompatActivity> activity){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        token = Constants.TOKEN_ID;;
        initDataGetFromIntent(savedInstanceState);
        mContext = this;
        unbinder = ButterKnife.bind(this);
        //Setting Dagger2

//        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
//        if (mCommonToolbar != null) {
//            initToolBar();
//            setSupportActionBar(mCommonToolbar);
//        }
        initDatas();
        configViews();
        initStatusBar();
    }

    protected void initDataGetFromIntent(Bundle savedInstanceState){
    }

    protected void avoidSemClipBoardManagerError() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.MANUFACTURER.equals("samsung")) {
                Object systemService = getSystemService(Class.forName("com.samsung.android.content.clipboard.SemClipboardManager"));
                Field context = systemService.getClass().getDeclaredField("context");
                context.setAccessible(true);
                context.set(systemService, getApplicationContext());
            }
        } catch (Exception e) { //ignored }
        }
    }

    protected void initStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            StatusBarMainUtil.setMiuiStatusBarIconDarkMode(this, true);
            StatusBarMainUtil.setFlymeStatusBarIconDarkMode(this, true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
                StatusBarMainUtil.setMiuiStatusBarIconDarkMode(this, false);
                StatusBarMainUtil.setFlymeStatusBarIconDarkMode(this, false);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        avoidSemClipBoardManagerError();
        unbinder.unbind();
        dismissDialog();
    }

    protected abstract int getLayoutId();

    protected abstract void initToolBar();

    protected abstract void initDatas();

    /**
     * config for View
     */
    protected abstract void configViews();

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    // dialog
    protected LoadingDialog getDialog() {
        if (dialog == null) {
            dialog = LoadingDialog.instance(this);
            dialog.setCancelable(true);
        }
        return dialog;
    }

    protected void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    protected void showDialog() {
        getDialog().show();
    }

    protected void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(statusBarColor);
        }
    }
}
