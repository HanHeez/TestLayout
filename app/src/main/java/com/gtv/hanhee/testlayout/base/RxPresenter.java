package com.gtv.hanhee.testlayout.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected String token;
    protected int currentWeek;
    protected int currentDay;
    protected String imageMom;
    protected String nameMom;
    protected String imageBaby;
    protected String nameBaby;
    protected int week;
    protected int day;

    protected T mView;
    protected CompositeDisposable compositeDisposable;

    protected void unSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            //TODO Change this if don't receiver data
        }
    }

    protected void addSubscrebe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
