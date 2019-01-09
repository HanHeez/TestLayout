package com.gtv.hanhee.testlayout.api;

import com.gtv.hanhee.testlayout.dagger2.PreferencesHelper;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Message;
import com.gtv.hanhee.testlayout.model.ShopBanner;
import com.gtv.hanhee.testlayout.model.Category;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.Shop;
import com.gtv.hanhee.testlayout.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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
    public Observable<List<Product>>getListProductRecommend(String accessToken, String categoryId, int skip, int limit) {
        return service.getListProductRecommend(accessToken, categoryId, skip, limit);
    }
    public Observable<List<Product>> getListProductByCategory(String accessToken, String categoryId, int skip, int limit) {
        return service.getListProductByCategory(accessToken, categoryId, skip, limit);
    }
    public Observable<List<Product>> getListProductBySubCategory(String accessToken, String subCategoryId, int skip, int limit) {
        return service.getListProductBySubCategory(accessToken, subCategoryId, skip, limit);
    }

//    Cart -----------------
    public Observable<Message> addProductToCart(String accessToken, String productId, int quantity) {
        return service.addProductToCart(accessToken, productId, quantity);
    }
    public Observable<Message> removeAllProductOnCart(String accessToken) {
        return service.removeAllProductOnCart(accessToken);
    }

//    User  ------------------


    public Observable<List<AddressInfo>> getListAddressInfo(String accessToken) {
        return service.getListAddressInfo(accessToken);
    }

    public Observable<Message> addAddressInfo(String accessToken, String fullName, String phoneNumber, String email, String address) {
        return service.addAddressInfo(accessToken, fullName, phoneNumber, email, address);
    }

    public Observable<Message> removeAddressInfo(String accessToken, String addressid) {
        return service.removeAddressInfo(accessToken, addressid);
    }

    public Observable<AddressInfo> getAddressInfoById(String accessToken, String addressid) {
        return service.getAddressInfoById(accessToken, addressid);
    }

    public Observable<Message> updateAddressInfo(String accessToken, String addressId, String fullName, String phoneNumber, String email, String address) {
        return service.updateAddressInfo(accessToken, addressId, fullName, phoneNumber, email, address);
    }

//    Order ----------------------

    public Observable<Message> orderAll(String accessToken, String fullName, String phoneNumber, String email, String address) {
        return service.orderAll(accessToken, fullName, phoneNumber, email, address);
    }
}

