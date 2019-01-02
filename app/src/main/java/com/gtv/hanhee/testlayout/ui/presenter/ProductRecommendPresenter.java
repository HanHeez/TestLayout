package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.ui.contract.ProductRecommendContract;
import com.gtv.hanhee.testlayout.ui.contract.ShopCategoryContact;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductRecommendPresenter extends RxPresenter<ProductRecommendContract.View> implements ProductRecommendContract.Presenter<ProductRecommendContract.View> {
    private BiboManager biboManager;

    @Inject
    public ProductRecommendPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }

    @Override
    public void getRecommendProductList(String accessToken, String id, int skip, int limit) {
        Disposable disposable = biboManager.getRecommendProductList(accessToken, id, skip, limit)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showRecommendProductList(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }
}

