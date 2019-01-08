package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.ui.contract.ShopSearchContract;
import com.gtv.hanhee.testlayout.ui.contract.UserInfoChooseContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserInfoChoosePresenter extends RxPresenter<UserInfoChooseContract.View> implements UserInfoChooseContract.Presenter<UserInfoChooseContract.View> {
    private BiboManager biboManager;

    @Inject
    public UserInfoChoosePresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }

    @Override
    public void getListAddressInfo(String accessToken) {
        Disposable disposable = biboManager.getListAddressInfo(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showListAddressInfo(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }
}
