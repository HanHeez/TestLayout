package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.SubCategory;

import java.util.List;

public interface ProductDetailContract {
    interface View extends BaseContract.BaseView {
        void showProduct(Product productResult);
        void showRecommendProductList(List<Product> recommendProductList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getProduct(String accessToken, String id);
        void getRecommendProductList(String accessToken, String id, int skip, int limit);
    }
}
