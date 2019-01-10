package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.ui.contract.ProfileUserContract;
import com.gtv.hanhee.testlayout.ui.contract.ProfileUserInfoContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileUserInfoPresenter extends RxPresenter<ProfileUserInfoContract.View> implements ProfileUserInfoContract.Presenter<ProfileUserInfoContract.View> {
    private BiboManager biboManager;

    @Inject
    public ProfileUserInfoPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }


    @Override
    public void getUserInfoById(String accessToken, String userId) {
        Disposable disposable = biboManager.getInfoUserById(accessToken, userId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showUserInfoByid(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }

    @Override
    public void getUserInfoMom(String accessToken) {
        Disposable disposable = biboManager.getInfoUserMom(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showUserInfoMom(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }
}
