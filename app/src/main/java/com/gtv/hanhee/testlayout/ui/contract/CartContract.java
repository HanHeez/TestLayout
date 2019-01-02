package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.Product;

import java.util.List;

public interface CartContract {

    interface View extends BaseContract.BaseView {
        void showCartProduct(List<Product> productList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getCartProduct();
    }
}
