package com.gtv.hanhee.testlayout.model.respository;

import android.arch.lifecycle.LiveData;

import com.google.gson.Gson;
import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.dao.ProductDao;
import com.gtv.hanhee.testlayout.model.database.AppDatabase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ProductRespository {

    private ProductDao productDao;
    private LiveData<List<Product>> listProductLiveData;


    @Inject
    public ProductRespository(@Named("ProductDao") ProductDao productDao){
        this.productDao=productDao;
    }
    public LiveData<List<Product>> getInfo() {
        updateInfo();
        return productDao.liveGetAll();
    }

    public void updateInfo() {
        MyApplication.mProductDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(List<Product> productList) {
                        for (int i=0; i<productList.size(); i++) {
                            int finalI = i;
                            Completable.fromAction(() -> MyApplication.mProductDao.insertAll(productList.get(finalI))).subscribeOn(Schedulers.io())
                                    .subscribe();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
