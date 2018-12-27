package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.ui.contract.ShopHomeContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopHomePresenter extends RxPresenter<ShopHomeContract.View> implements ShopHomeContract.Presenter<ShopHomeContract.View> {

    private BiboManager biboManager;

    @Inject
    public ShopHomePresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }

    @Override
    public void getShopBanner(String accessToken) {
        Disposable disposable = biboManager.getBanner(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showShopBanner(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }

    @Override
    public void getListProduct(String accessToken) {
        Disposable disposable = biboManager.getListProduct(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showListProduct(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );

        addSubscrebe(disposable);
    }
}
