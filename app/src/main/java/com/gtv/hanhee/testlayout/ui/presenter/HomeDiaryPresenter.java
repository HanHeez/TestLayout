package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.ui.contract.HomeDiaryContract;

import javax.inject.Inject;

public class HomeDiaryPresenter extends RxPresenter<HomeDiaryContract.View> implements HomeDiaryContract.Presenter<HomeDiaryContract.View> {
    private BiboManager biboManager;

    @Inject
    public HomeDiaryPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }



}
