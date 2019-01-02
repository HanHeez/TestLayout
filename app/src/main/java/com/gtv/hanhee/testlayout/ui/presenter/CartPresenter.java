package com.gtv.hanhee.testlayout.ui.presenter;

import android.util.Log;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.contract.CartContract;
import com.gtv.hanhee.testlayout.ui.contract.ProductDetailContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CartPresenter extends RxPresenter<CartContract.View> implements CartContract.Presenter<CartContract.View> {
    private BiboManager biboManager;

    @Inject
    public CartPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }


    @Override
    public void getCartProduct() {
        MyApplication.mProductDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(List<Product> productList) {
                       mView.showCartProduct(productList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }
                });
    }
}
