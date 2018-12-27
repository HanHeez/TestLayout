package com.gtv.hanhee.testlayout.ui.contract;

import com.gtv.hanhee.testlayout.base.BaseContract;
import com.gtv.hanhee.testlayout.model.BannerDetail;
import com.gtv.hanhee.testlayout.model.Product;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface ShopHomeContract {

    interface View extends BaseContract.BaseView {
        void showShopBanner(List<BannerDetail> bannerDetailListResult);
        void showListProduct(List<Product> productList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getShopBanner(String accessToken);
        void getListProduct(String accessToken);
    }
}
