package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.ui.contract.ShopContract;
import com.gtv.hanhee.testlayout.ui.contract.UserContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter extends RxPresenter<UserContract.View> implements UserContract.Presenter<UserContract.View> {
    private BiboManager biboManager;

    @Inject
    public UserPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }

    @Override
    public void getUserSettingList(String accessToken) {
        Disposable disposable = biboManager.getUserSettingList(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showUserSettingList(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }

    @Override
    public void getUserServiceList(String accessToken) {
        Disposable disposable = biboManager.getUserServiceList(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showUserServiceList(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);

    }
}