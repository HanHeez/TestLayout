package com.gtv.hanhee.testlayout.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.Shop;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;


@Dao
public interface ProductDao {
    @Query("SELECT * FROM products")
    Single<List<Product>> getAll();

    @Query("select * from products where id = :productId")
    LiveData<Product> loadProduct(int productId);

    @Query("SELECT shop_id, shop_name, shop_avatar, shop_description, shop_shipper FROM products")
    Shop getShopFromProduct();


    @Query("select * from products where id = :productId")
    Product loadProductSync(int productId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Product... products);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM products")
    void deleteAllDataFromProduct();

    @Delete
    void deleteProducts(Product... products);

    @Delete
    void deleteProductLists(List<Product> productsList);


    @Update
    public void updateProducts(Product... products);
}
