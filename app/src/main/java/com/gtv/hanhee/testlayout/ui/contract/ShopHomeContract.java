package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.ShopBanner;
import com.gtv.hanhee.testlayout.model.Product;

import java.util.List;

public interface ShopHomeContract {

    interface View extends BaseContract.BaseView {
        void showShopBanner(List<ShopBanner> shopBannerListResult);
        void showListProductNewest(List<Product> productList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getShopBanner(String accessToken, int skip, int limit);
        void getListProductNewest(String accessToken, int skip, int limit);
    }
}
