package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.contract.CartContract;
import com.gtv.hanhee.testlayout.ui.contract.HomeRemindContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeRemindPresenter extends RxPresenter<HomeRemindContract.View> implements HomeRemindContract.Presenter<HomeRemindContract.View> {
    private BiboManager biboManager;

    @Inject
    public HomeRemindPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }



}

