package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.BannerDetail;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.SubCategory;

import java.util.List;

public interface ShopCategoryContact {

    interface View extends BaseContract.BaseView {
        void showListProduct(List<Product> productList);
        void showListSubCategory(List<SubCategory> subCategoryList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getListSubCategory(String accessToken, String groupId);
        void getListProduct(String accessToken);
    }
}
