package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.ui.contract.ShopContract;
import com.gtv.hanhee.testlayout.ui.contract.ShopSearchContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShopSearchPresenter extends RxPresenter<ShopSearchContract.View> implements ShopSearchContract.Presenter<ShopSearchContract.View> {
    private BiboManager biboManager;

    @Inject
    public ShopSearchPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }

}
