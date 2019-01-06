//package com.gtv.hanhee.testlayout.model.dao;
//
//import android.arch.lifecycle.LiveData;
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Delete;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.OnConflictStrategy;
//import android.arch.persistence.room.Query;
//import android.arch.persistence.room.Update;
//
//import com.gtv.hanhee.testlayout.model.Shop;
//
//import java.util.List;
//
//import io.reactivex.Single;
//
//@Dao
//public interface ShopDao {
//    @Query("SELECT * FROM shops")
//    Single<List<Shop>> getAll();
//
//    @Query("SELECT * FROM shops")
//    LiveData<List<Shop>> liveGetAll();
//
//    @Query("select * from shops where shop_id = :shopId")
//    Single<Shop> findShop(String shopId);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertAll(Shop... shops);
//
//    @Delete
//    void delete(Shop shop);
//
//    @Query("DELETE FROM shops")
//    void deleteAllDataFromShop();
//
//    @Delete
//    void deleteShops(Shop... shops);
//
//    @Delete
//    void deleteShopLists(List<Shop> shopList);
//
//
//    @Update
//    void updateShop(Shop... shops);
//}
