package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.ui.contract.ShopContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopPresenter extends RxPresenter<ShopContract.View> implements ShopContract.Presenter<ShopContract.View> {
    private BiboManager biboManager;

    @Inject
    public ShopPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }


    @Override
    public void getListCategory(String accessToken) {
        Disposable disposable = biboManager.getListCategory(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showListCategory(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }
}
