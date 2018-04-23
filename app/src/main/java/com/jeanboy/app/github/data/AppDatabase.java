package com.jeanboy.app.github.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jeanboy.app.github.data.dao.UserDao;
import com.jeanboy.app.github.data.entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    private static final String DATABASE_NAME = "app-db";

    public AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

    public void onDestroy() {
        instance = null;
    }

    @Override
    public void clearAllTables() {

    }

    public abstract UserDao userDao();
}
