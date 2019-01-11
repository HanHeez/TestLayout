package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.contract.CartContract;
import com.gtv.hanhee.testlayout.ui.contract.OrderListContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class OrderListPresenter extends RxPresenter<OrderListContract.View> implements OrderListContract.Presenter<OrderListContract.View> {
    private BiboManager biboManager;

    @Inject
    public OrderListPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }

}

