package com.gtv.hanhee.testlayout.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.util.Log;

import com.gtv.hanhee.testlayout.base.MyApplication;
import com.gtv.hanhee.testlayout.model.respository.ProductRespository;

import java.util.List;

import javax.inject.Inject;

public class ProductViewModel extends ViewModel {

    private ProductRespository productRespository;
    @Inject
    public ProductViewModel(ProductRespository productRespository){
        this.productRespository = productRespository;
    }
    public LiveData<List<Product>> getInfos(){
        if (productRespository==null){
            Log.e("---->","isNull");
        }
        return  productRespository.getInfo();
    }
}
