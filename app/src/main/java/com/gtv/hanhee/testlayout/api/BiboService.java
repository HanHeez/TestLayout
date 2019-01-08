package com.gtv.hanhee.testlayout.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gtv.hanhee.testlayout.api.support.Logger;
import com.gtv.hanhee.testlayout.model.AddressInfo;
import com.gtv.hanhee.testlayout.model.Category;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.ShopBanner;
import com.gtv.hanhee.testlayout.model.SubCategory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface BiboService {

    class Factory {

        public static BiboService makeBiboService(Context context) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new Logger());
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(logging);

            OkHttpClient okHttpClient = builder.build();

            Gson gson = new GsonBuilder().setLenient().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_ENDPOINT)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            return retrofit.create(BiboService.class);
        }

    }

//    APi here-----------

    //    Shop Product ---------
    @GET("v2/product/sub_categories")
    Observable<List<SubCategory>> getListSubCategory(
            @Header("Authorization") String accessToken,
            @Query("category") String categoryId, @Query("skip") int skip, @Query("limit") int limit
    );

    @GET("v2/product/categories")
    Observable<List<Category>> getListCategory(@Header("Authorization") String accessToken);

    @GET("v2/product/banners")
    Observable<List<ShopBanner>> getShopHomeBanner(@Header("Authorization") String accessToken, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product")
    Observable<List<Product>> getListProductByCategory(@Header("Authorization") String accessToken, @Query("category") String categoryId, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product/new")
    Observable<List<Product>> getListProductNewest(@Header("Authorization") String accessToken, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product")
    Observable<List<Product>> getListProductBySubCategory(@Header("Authorization") String accessToken, @Query("subCategory") String subCategoryId, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product/recommends")
    Observable<List<Product>> getListProductRecommend(@Header("Authorization") String accessToken, @Query("categoryId") String categoryId, @Query("skip") int skip, @Query("limit") int limit);

    @GET("v2/product/detail")
    Observable<Product> getProduct(@Header("Authorization") String accessToken, @Query("_id") String id);


    //    User Product -----------
    @GET("v2/product/user_info")
    Observable<List<AddressInfo>> getListAddressInfo(@Header("Authorization") String accessToken);

}
