package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.ui.contract.OrderContract;
import com.gtv.hanhee.testlayout.ui.contract.UserInfoAddContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserInfoAddPresenter extends RxPresenter<UserInfoAddContract.View> implements UserInfoAddContract.Presenter<UserInfoAddContract.View> {
    private BiboManager biboManager;

    @Inject
    public UserInfoAddPresenter(BiboManager biboManager) {
        this.biboManager = biboManager;
    }



    @Override
    public void addAddressInfo(String accessToken, String fullname, String phoneNumber, String email, String address) {
        Disposable disposable = biboManager.addAddressInfo(accessToken, fullname, phoneNumber, email, address)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.successAddAddressInfo(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }

    @Override
    public void getAddressInfoById(String accessToken, String addressId) {
        Disposable disposable = biboManager.getAddressInfoById(accessToken, addressId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showAddressInfoById(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }

    @Override
    public void updateAddressInfo(String accessToken, String addressId, String fullname, String phoneNumber, String email, String address) {
        Disposable disposable = biboManager.updateAddressInfo(accessToken, addressId, fullname, phoneNumber, email, address)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.successUpdateAddressInfo(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }
}

