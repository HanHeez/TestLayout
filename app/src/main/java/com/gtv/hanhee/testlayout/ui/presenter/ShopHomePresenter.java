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
    public void getShopBanner(String accessToken, int skip, int limit) {
        Disposable disposable = biboManager.getShopHomeBanner(accessToken, skip, limit)
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
    public void getListProductNewest(String accessToken, int skip, int limit) {
        Disposable disposable = biboManager.getListProductNewest(accessToken, skip, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showListProductNewest(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );

        addSubscrebe(disposable);
    }
}
