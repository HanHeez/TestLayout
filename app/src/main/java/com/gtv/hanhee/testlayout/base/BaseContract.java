package com.gtv.hanhee.testlayout.base;

public interface BaseContract {

    interface BasePresenter<T> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {

        void showError();

        void complete();

    }
}
