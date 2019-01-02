package com.gtv.hanhee.testlayout.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.dao.ProductDao;


@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}
