package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.SubCategory;

import java.util.List;

public interface ProductDetailContract {
    interface View extends BaseContract.BaseView {
        void showProduct(Product productResult);
        void showRecommendProductList(List<Product> recommendProductList);
        void showListProductBySubCategory(List<Product> productListResult);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getProduct(String accessToken, String id);
        void getRecommendProductList(String accessToken, String id, int skip, int limit);
        void getListProductBySubCategory(String accessToken, String subCategoryId, int skip, int limit);
    }
}
