package com.gtv.hanhee.testlayout.api;

import com.gtv.hanhee.testlayout.dagger2.PreferencesHelper;
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

    public Observable<List<Product>> getRecommendProductList(String accessToken, String id, int skip, int limit) {
        List<Product> recommendProductList = new ArrayList<>();
        recommendProductList.add(new Product("Sữa ông thọ", "1", 200000, 180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        recommendProductList.add(new Product("Đào tươi", "2", 40000, 30000, "Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        recommendProductList.add(new Product("Bánh đậu xanh", "3", 40000, 30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        recommendProductList.add(new Product("Sữa ông thọ", "4", 200000, 180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        recommendProductList.add(new Product("Đào tươi", "5", 40000, 30000, "", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        recommendProductList.add(new Product("Bánh đậu xanh", "6", 40000, 30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        recommendProductList.add(new Product("Sữa ông thọ", "7", 200000, 180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        recommendProductList.add(new Product("Sữa ông thọ", "8", 200000, 180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        recommendProductList.add(new Product("Đào tươi", "9", 40000, 30000, "Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        recommendProductList.add(new Product("Bánh đậu xanh", "10", 40000, 30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        recommendProductList.add(new Product("Sữa ông thọ", "11", 200000, 180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        recommendProductList.add(new Product("Đào tươi", "12", 40000, 30000, "", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        recommendProductList.add(new Product("Bánh đậu xanh", "13", 40000, 30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        recommendProductList.add(new Product("Sữa ông thọ", "14", 200000, 180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));

        List<Product> returnList = new ArrayList<>();
        int countLimit = 0;
        for (int i = skip; i < recommendProductList.size(); i++) {
            returnList.add(recommendProductList.get(i));
            countLimit++;
            if (countLimit == limit) return Observable.just(returnList);
        }
        return Observable.just(returnList);
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

