package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.ui.contract.ProfileUserContract;
import com.gtv.hanhee.testlayout.ui.contract.ShopContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileUserPresenter extends RxPresenter<ProfileUserContract.View> implements ProfileUserContract.Presenter<ProfileUserContract.View> {
    private BiboManager biboManager;

    @Inject
    public ProfileUserPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }



}

