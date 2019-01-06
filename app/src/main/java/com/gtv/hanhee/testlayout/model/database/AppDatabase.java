package com.gtv.hanhee.testlayout.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.gtv.hanhee.testlayout.model.Product;
import com.gtv.hanhee.testlayout.model.Shop;
import com.gtv.hanhee.testlayout.model.dao.ProductDao;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context) {
        synchronized (AppDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "product-store")
                        .build();
            }
            return INSTANCE;
        }
    }

}
