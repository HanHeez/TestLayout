package com.gtv.hanhee.testlayout.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gtv.hanhee.testlayout.api.support.Logger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface StorageService {

    class Factory {

        public static StorageService makeStorageService(Context context) {
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

            return retrofit.create(StorageService.class);
        }

    }

//    @Multipart
//    @POST("v1/storage/single")
//    Observable<UploadDetails> uploadImageVideo(@Header("Authorization") String accessToken, @Part MultipartBody.Part file);

}
