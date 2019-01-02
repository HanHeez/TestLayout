package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.Product;

import java.util.List;

public interface ShopContract {
    interface View extends BaseContract.BaseView {

    }
    interface Presenter<T> extends BaseContract.BasePresenter<T> {
    }
}
