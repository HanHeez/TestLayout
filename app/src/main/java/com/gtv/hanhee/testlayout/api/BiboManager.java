package com.gtv.hanhee.testlayout.api;

import com.gtv.hanhee.testlayout.dagger2.PreferencesHelper;
import com.gtv.hanhee.testlayout.model.BannerDetail;
import com.gtv.hanhee.testlayout.model.Category;
import com.gtv.hanhee.testlayout.model.CategoryService;
import com.gtv.hanhee.testlayout.model.PostNews;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.Shop;
import com.gtv.hanhee.testlayout.model.SubCategory;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
        productList.add(new Product("Sữa ông thọ", "7", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        productList.add(new Product("Đào tươi", "8", 40000,  30000, "Đào nhập khẩu Trung Quốc", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        productList.add(new Product("Bánh đậu xanh", "9", 40000,  30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        productList.add(new Product("Sữa ông thọ", "10", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        productList.add(new Product("Đào tươi", "11", 40000,  30000, "Đào nhập khẩu Trung Quốc", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        productList.add(new Product("Bánh đậu xanh", "12", 40000,  30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        return Observable.just(productList);
    }

    public Observable<List<SubCategory>> getListSubCategory(String accessToken, String categoryId) {
        List<SubCategory> subCategoryList = new ArrayList<>();
        subCategoryList.add(new SubCategory("1", "Sữa ông thọ",   "Category Description","https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        subCategoryList.add(new SubCategory("2", "Sữa bột",   "Category Description","https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        subCategoryList.add(new SubCategory("3", "Sữa nước",   "Category Description","https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        subCategoryList.add(new SubCategory("4", "Sữa đặc",   "Category Description","https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        subCategoryList.add(new SubCategory("4", "Sữa đặc",   "Category Description","https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        subCategoryList.add(new SubCategory("4", "Sữa đặc",   "Category Description","https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));

//        return service.getListSubCategory(accessToken, categoryId);
        return Observable.just(subCategoryList);
    }


    public Observable<Product> getProduct(String accessToken, String id) {
        List<String> images = new ArrayList<>();
        images.add("https://anh.24h.com.vn/upload/1-2016/images/2016-03-25/1458876306-1457584417-1.jpg");
        images.add("http://thuvienanhdep.net/wp-content/uploads/2015/10/nhung-hinh-anh-hot-girl-dang-yeu-voi-chiec-ao-so-mi-ket-hop-chan-vay-61.jpg");
        images.add("https://thoitrangtre.biz/wp-content/uploads/2014/08/bo-vest-cong-so-giao-vien-2014-12.jpg");
        images.add("https://i.a4vn.com/2018/10/2/hot-girl-van-phong-mac-do-cong-so-sexy-goi-cam-den-khong-ngo-26cbc2.jpg");
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("1", "Quần áo", "quàn áo đẹp"));
        categoryList.add(new Category("2", "Thời trang", "quàn áo đẹp"));
        categoryList.add(new Category("3", "Phụ kiện", "quàn áo đẹp"));
        Shop shop = new Shop("1", "Shop HanHee TĐLQ", "https://img.webtretho.com/forum/images/wtt_v2/lamdep.png","Shop luôn bán giá đúng","T-MALL");
        Product product = new Product("Sữa ông thọ", "2", 160000,  70000000, "Sữa 100% nguyên chất đấy a e ơi", "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg", true, images, categoryList, shop);
         return Observable.just(product);
    }

    public Observable<List<Product>> getRecommendProductList(String accessToken, String id, int skip, int limit) {

        List<Product> recommendProductList = new ArrayList<>();
        recommendProductList.add(new Product("Sữa ông thọ", "1", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        recommendProductList.add(new Product("Đào tươi", "2", 40000,  30000, "Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        recommendProductList.add(new Product("Bánh đậu xanh", "3", 40000,  30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        recommendProductList.add(new Product("Sữa ông thọ", "4", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        recommendProductList.add(new Product("Đào tươi", "5", 40000,  30000, "", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        recommendProductList.add(new Product("Bánh đậu xanh", "6", 40000,  30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        recommendProductList.add(new Product("Sữa ông thọ", "7", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        recommendProductList.add(new Product("Sữa ông thọ", "8", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        recommendProductList.add(new Product("Đào tươi", "9", 40000,  30000, "Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc Đào nhập khẩu Trung Quốc", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        recommendProductList.add(new Product("Bánh đậu xanh", "10", 40000,  30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        recommendProductList.add(new Product("Sữa ông thọ", "11", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));
        recommendProductList.add(new Product("Đào tươi", "12", 40000,  30000, "", 0, 278, "https://media.healthplus.vn/Images/Uploaded/Share/2016/06/17/FB_IMG_1465116764246_1.jpg"));
        recommendProductList.add(new Product("Bánh đậu xanh", "13", 40000,  30000, "Bánh Việt Nam Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam  Bánh Việt Nam ", 60, 278, "https://thucthan.com/media/2018/02/cach-lam-banh-gato/cach-lam-banh-gato.jpg"));
        recommendProductList.add(new Product("Sữa ông thọ", "14", 200000,  180000, "Sữa 100% nguyên chất", 50, 25678, "https://media-ak.static-adayroi.com/sys_master/h9b/hdc/10214994542622.jpg"));

        List<Product> returnList = new ArrayList<>();
        int countLimit = 0;
        for (int i=skip; i<recommendProductList.size();i ++) {
            returnList.add(recommendProductList.get(i));
            countLimit++;
            if (countLimit==limit) return Observable.just(returnList);
        }
        return Observable.just(returnList);
    }

    public Observable<Product> getListProductByCategory(String accessToken, String categoryId) {
        return service.getListProductByCategory(accessToken, categoryId);
    }

    public Observable<Product> getListProductBySubCategory(String accessToken, String subCategoryId) {
        return service.getListProductBySubCategory(accessToken, subCategoryId);
    }
//
//    public Observable<Message> sendEmail(String email) {
//        return service.sendEmail(email);
//    }
//
//
}

