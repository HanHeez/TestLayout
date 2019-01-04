package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.SubCategory;

import java.util.List;

public interface ShopCategoryContact {

    interface View extends BaseContract.BaseView {
        void showListProductByCategory(List<Product> productList);
        void showListSubCategory(List<SubCategory> subCategoryList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getListSubCategory(String accessToken, String categoryId, int skip, int limit);
        void getListProductByCategory(String accessToken, String categoryId, int skip, int limit);
    }
}
