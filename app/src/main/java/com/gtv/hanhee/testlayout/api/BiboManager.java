package com.gtv.hanhee.testlayout.api;

import com.gtv.hanhee.testlayout.dagger2.PreferencesHelper;
import com.gtv.hanhee.testlayout.model.BannerDetail;
import com.gtv.hanhee.testlayout.model.CategoryService;
import com.gtv.hanhee.testlayout.model.PostNews;


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

//
//    public Observable<Message> sendEmail(String email) {
//        return service.sendEmail(email);
//    }
//
//
}

