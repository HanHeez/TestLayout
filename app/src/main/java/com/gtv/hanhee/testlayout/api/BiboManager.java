package com.gtv.hanhee.testlayout.api;

import com.gtv.hanhee.testlayout.dagger2.PreferencesHelper;
import com.gtv.hanhee.testlayout.model.BannerDetail;
import com.gtv.hanhee.testlayout.model.CategoryService;
import com.gtv.hanhee.testlayout.model.PostNews;
import com.gtv.hanhee.testlayout.model.Product;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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



    public Observable<List<BannerDetail>> getBanner(String accessToken) {
        List<BannerDetail> bannerDetailList = new ArrayList<>();
        bannerDetailList.add(new BannerDetail("1", "https://upload.wikimedia.org/wikipedia/commons/c/c6/Inside_a_christmas_shop.jpg"));
        bannerDetailList.add(new BannerDetail("1", "https://stockeldpark.co.uk/wp-content/uploads/2016/04/Stockeld-Christmas-Shop-5.jpg"));
        bannerDetailList.add(new BannerDetail("1", "http://bateauivre.com/wp-content/uploads/2016/09/1dcbd2bc886a640d56b19b15b1e0f68d.jpg"));
        bannerDetailList.add(new BannerDetail("1", "http://bateauivre.com/wp-content/uploads/2016/09/1dcbd2bc886a640d56b19b15b1e0f68d.jpg"));
        return Observable.just(bannerDetailList);
    }

    public Observable<List<Product>> getListProduct(String accessToken) {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Sữa ông thọ", "1", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        productList.add(new Product("Đào tươi", "2", 40000,  30000, "Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        productList.add(new Product("Bánh đậu xanh", "3", 40000,  30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        productList.add(new Product("Sữa ông thọ", "4", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        productList.add(new Product("Đào tươi", "5", 40000,  30000, "", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        productList.add(new Product("Bánh đậu xanh", "6", 40000,  30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        productList.add(new Product("Sữa ông thọ", "1", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        productList.add(new Product("Đào tươi", "2", 40000,  30000, "Đào nhập khẩu Trung Quốc", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        productList.add(new Product("Bánh đậu xanh", "3", 40000,  30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        productList.add(new Product("Sữa ông thọ", "4", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        productList.add(new Product("Đào tươi", "5", 40000,  30000, "Đào nhập khẩu Trung Quốc", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        productList.add(new Product("Bánh đậu xanh", "6", 40000,  30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        return Observable.just(productList);
    }
//
//    public Observable<Message> sendEmail(String email) {
//        return service.sendEmail(email);
//    }
//
//
}

