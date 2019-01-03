package com.gtv.hanhee.testlayout.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.api.Constants;
import com.gtv.hanhee.testlayout.dagger2.component.ActivityComponent;

import com.gtv.hanhee.testlayout.dagger2.component.DaggerActivityComponent;
import com.gtv.hanhee.testlayout.dagger2.module.ActivityModule;
import com.gtv.hanhee.testlayout.ui.customview.LoadingDialog;
import com.gtv.hanhee.testlayout.utils.StatusBarMainUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment {

    protected View parentView;
    protected FragmentActivity activity;
    protected LayoutInflater inflater;
    protected Context mContext;
    private Unbinder unbinder;
    private LoadingDialog dialog;
    protected String token;
    protected CompositeDisposable mDisposable;

    public abstract @LayoutRes
    int getLayoutResId();

    protected ActivityComponent mActivityComponent;

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(mContext))
                    .applicationComponent(MyApplication.get(mContext).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        parentView = inflater.inflate(getLayoutResId(), container, false);

          activity = getSupportActivity();
        mContext = activity;
        this.inflater = inflater;
        return parentView;
    }

    protected void initStatusBar() {
        Window window = activity.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.white));
            StatusBarMainUtil.setMiuiStatusBarIconDarkMode(activity, true);
            StatusBarMainUtil.setFlymeStatusBarIconDarkMode(activity, true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(activity, R.color.black));
                StatusBarMainUtil.setMiuiStatusBarIconDarkMode(activity, false);
                StatusBarMainUtil.setFlymeStatusBarIconDarkMode(activity, false);
            }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataGetFromArgument(savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        token = Constants.TOKEN_ID;
        initStatusBar();
        attachView();
        initDatas();
        configViews();


    }

    protected void initDataGetFromArgument(Bundle savedInstanceState) {

    }

    public abstract void attachView();

    public abstract void initDatas();

    public abstract void configViews();

    protected void addDisposable(Disposable d){
        if (mDisposable == null){
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(d);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder.unbind();

        if (mDisposable != null){
            mDisposable.clear();
        }
    }


    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    public Context getApplicationContext() {
        return this.activity == null ? (getActivity() == null ? null : getActivity()
                .getApplicationContext()) : this.activity.getApplicationContext();
    }

    protected View getParentView() {
        return parentView;
    }

    public LoadingDialog getDialog() {
        if (dialog == null) {
            dialog = LoadingDialog.instance(getActivity());
            dialog.setCancelable(false);
        }
        return dialog;
    }

    public void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    public void showDialog() {
        getDialog().show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    //view action
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


}