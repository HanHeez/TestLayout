package com.gtv.hanhee.testlayout.api;

import com.gtv.hanhee.testlayout.R;
import com.gtv.hanhee.testlayout.dagger2.PreferencesHelper;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Category;
import com.gtv.hanhee.testlayout.model.CategoryService;
import com.gtv.hanhee.testlayout.model.Message;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ShopBanner;
import com.gtv.hanhee.testlayout.model.SubCategory;
import com.gtv.hanhee.testlayout.model.User;
import com.gtv.hanhee.testlayout.model.UserSetting;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

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
        return service.getListSubCategory(accessToken, categoryId, skip, limit);
    }

    public Observable<Product> getProduct(String accessToken, String id) {
        return service.getProduct(accessToken, id);
    }

    public Observable<List<Product>> getListProductRecommend(String accessToken, String categoryId, int skip, int limit) {
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

//    User Address Shop ------------------


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

    //    User App ----------
    public Observable<User> getInfoUserById(String accessToken, String userId) {
        return service.getInfoUserById(accessToken, userId);
    }

    public Observable<User> getInfoUserMom(String accessToken) {
        return service.getInfoUserMom(accessToken);
    }

    public Observable<List<CategoryService>> getUserServiceList(String accessToken) {
        List<CategoryService> userSettingList = new ArrayList<>();
        userSettingList.add(new CategoryService("Bác sĩ",R.drawable.tools_img_doctor));
        userSettingList.add(new CategoryService("Tắm cho bé",R.drawable.home_icon_window_girl));

        return Observable.just(userSettingList);
    }

    public Observable<List<UserSetting>> getUserSettingList(String accessToken) {
        List<UserSetting> userSettingList = new ArrayList<>();
        userSettingList.add(new UserSetting("Khóa biểu",R.drawable.home_ic_record));
        userSettingList.add(new UserSetting("Đơn hàng",R.drawable.me_icon_dingdan));
        userSettingList.add(new UserSetting("Chú ý",R.drawable.me_icon_care));
        userSettingList.add(new UserSetting("Thảo luận",R.drawable.me_icon_topic));
        userSettingList.add(new UserSetting("Sưu tập",R.drawable.me_icon_collect));
        userSettingList.add(new UserSetting("Nhật ký",R.drawable.me_icon_diary));
        userSettingList.add(new UserSetting("Thông báo",R.drawable.me_icon_remind));
        userSettingList.add(new UserSetting("Phản hồi",R.drawable.me_icon_feedback));
        userSettingList.add(new UserSetting("Cài đặt",R.drawable.me_icon_set));
        return Observable.just(userSettingList);
    }

//    Order ----------------------

    public Observable<Message> orderAll(String accessToken, String fullName, String phoneNumber, String email, String address) {
        return service.orderAll(accessToken, fullName, phoneNumber, email, address);
    }
}

