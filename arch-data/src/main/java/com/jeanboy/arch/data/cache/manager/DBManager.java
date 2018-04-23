package com.jeanboy.arch.data.cache.manager;

import android.annotation.SuppressLint;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

/**
 * Created by jeanboy on 2016/12/9.
 */

public class DBManager {

    private Context context;
    private String dbName;
    private AppDatabase appDatabase;

    @SuppressLint("StaticFieldLeak")
    private static DBManager instance;

    public static DBManager getInstance() {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager();
                }
            }
        }
        return instance;
    }

    private DBManager() {
    }

    public void build(Context context, String dbName) {
        this.context = context.getApplicationContext();
        this.dbName = dbName;
    }

    public void onDestory() {
        context = null;
        instance = null;
    }

    public AppDatabase getDatabase() {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, dbName).build();
        }
        return appDatabase;
    }

    public void toMigration() {
        Migration MIGRATION_1_2 = new Migration(1, 2) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                        + "`name` TEXT, PRIMARY KEY(`id`))");
            }
        };
    }
}
