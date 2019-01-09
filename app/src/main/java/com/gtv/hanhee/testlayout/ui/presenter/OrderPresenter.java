package com.gtv.hanhee.testlayout.ui.presenter;

import com.gtv.hanhee.testlayout.api.BiboManager;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.base.RxPresenter;
import com.gtv.hanhee.testlayout.model.Message;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ProductSection;
import com.gtv.hanhee.testlayout.ui.contract.CartContract;
import com.gtv.hanhee.testlayout.ui.contract.OrderContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class OrderPresenter extends RxPresenter<OrderContract.View> implements OrderContract.Presenter<OrderContract.View> {
    private BiboManager biboManager;

    @Inject
    public OrderPresenter(BiboManager biboManager) {
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

    @Override
    public void getProduct(String accessToken, String id) {
        Disposable disposable = biboManager.getProduct(accessToken, id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showProduct(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }

    @Override
    public void getListAddressInfo(String accessToken) {
        Disposable disposable = biboManager.getListAddressInfo(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.showListAddressInfo(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }

    @Override
    public void orderAll(String accessToken, String fullName, String phoneNumber, String email, String address) {
        Disposable disposable = biboManager.orderAll(accessToken, fullName, phoneNumber, email, address)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.successOrderAll(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);
    }

    @Override
    public void addProductToCart(String accessToken, List<ProductSection> productSectionList) {
        List<Observable<Message>> productList = new ArrayList<>();
        for (int i=0; i<productSectionList.size();i++) {
            if (!productSectionList.get(i).isHeader ) {
                Observable<Message> productObservable = biboManager.addProductToCart(accessToken, productSectionList.get(i).t.getId(), productSectionList.get(i).t.getOrderAmount());
                productList.add(productObservable);
            }
        }

        Observable.concat(productList).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Message>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Message message) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onComplete() {
                        mView.successAddProductToCart(new Message("thành công"));
                    }
                });


    }

    @Override
    public void removeAllProductOnCart(String accessToken) {
        Disposable disposable = biboManager.removeAllProductOnCart(accessToken)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        (beans) -> {
                            mView.successRemoveAllProductOnCart(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                        }
                );
        addSubscrebe(disposable);

    }

}

