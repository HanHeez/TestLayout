package com.gtv.hanhee.testlayout.api;

import com.gtv.hanhee.testlayout.dagger2.PreferencesHelper;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.ShopBanner;
import com.gtv.hanhee.testlayout.model.Category;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.Shop;
import com.gtv.hanhee.testlayout.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public class BiboManager {

    private final BiboService service;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public BiboManager(BiboService biboService,
                       PreferencesHelper preferencesHelper) {
        service = biboService;
        mPreferencesHelper = preferencesHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }


    //    Setting Api ------------------------------------
    public Observable<List<Category>> getListCategory(String accessToken) {
        return service.getListCategory(accessToken);
    }


    public Observable<List<ShopBanner>> getShopHomeBanner(String accessToken, int skip, int limit) {
        return service.getShopHomeBanner(accessToken, skip, limit);
    }

    public Observable<List<Product>> getListProductNewest(String accessToken, int skip, int limit) {
        return service.getListProductNewest(accessToken, skip, limit);
    }

    public Observable<List<SubCategory>> getListSubCategory(String accessToken, String categoryId, int skip, int limit) {
        return service.getListSubCategory(accessToken, categoryId,  skip, limit);
    }


    public Observable<Product> getProduct(String accessToken, String id) {
        return service.getProduct(accessToken,id);
    }

    public Observable<List<AddressInfo>> getListAddressInfo(String accessToken) {
        return service.getListAddressInfo(accessToken);
    }

    public Observable<String> sendAddressInfo(String accessToken) {
        return Observable.just("thêm địa chỉ thành công");
    }

    public Observable<List<Product>>getListProductRecommend(String accessToken, String categoryId, int skip, int limit) {
        return service.getListProductRecommend(accessToken, categoryId, skip, limit);
    }

    public Observable<List<Product>> getListProductByCategory(String accessToken, String categoryId, int skip, int limit) {
        return service.getListProductByCategory(accessToken, categoryId, skip, limit);
    }

    public Observable<List<Product>> getListProductBySubCategory(String accessToken, String subCategoryId, int skip, int limit) {
        return service.getListProductBySubCategory(accessToken, subCategoryId, skip, limit);
    }
//
//    public Observable<Message> sendEmail(String email) {
//        return service.sendEmail(email);
//    }
//
//
}

