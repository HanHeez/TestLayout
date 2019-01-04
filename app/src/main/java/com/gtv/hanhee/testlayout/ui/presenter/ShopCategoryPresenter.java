package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.ui.contract.ShopCategoryContact;
import com.gtv.hanhee.testlayout.ui.contract.ShopHomeContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopCategoryPresenter extends RxPresenter<ShopCategoryContact.View> implements ShopCategoryContact.Presenter<ShopCategoryContact.View> {
    private BiboManager biboManager;

    @Inject
    public ShopCategoryPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }

    @Override
    public void getListSubCategory(String accessToken, String categoryId, int skip, int limit) {
        Disposable disposable = biboManager.getListSubCategory(accessToken, categoryId, skip, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showListSubCategory(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }

    @Override
    public void getListProductByCategory(String accessToken, String categoryId, int skip, int limit) {
        Disposable disposable = biboManager.getListProductByCategory(accessToken, categoryId, skip, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showListProductByCategory(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }

}

