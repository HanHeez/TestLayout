package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.Product;

import java.util.List;

public interface ProductRecommendContract {
    interface View extends BaseContract.BaseView {
        void showRecommendProductList(List<Product> recommendProductList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getRecommendProductList(String accessToken, String id, int skip, int limit);
    }
}
