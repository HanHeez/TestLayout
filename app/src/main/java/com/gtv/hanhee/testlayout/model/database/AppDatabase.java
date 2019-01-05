package com.gtv.hanhee.testlayout.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.gtv.hanhee.testlayout.model.Product;
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

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ProductDao mDao;
        String[] words = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(AppDatabase db) {
            mDao = db.productDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAllDataFromProduct();

//            for (int i = 0; i <= words.length - 1; i++) {
//                Word word = new Word(words[i]);
//                mDao.insert(word);
//            }
            return null;
        }
    }
}
