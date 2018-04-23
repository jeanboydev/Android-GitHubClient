package com.jeanboy.arch.data.cache.manager;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jeanboy.arch.data.cache.database.dao.UserModelDao;
import com.jeanboy.arch.data.cache.database.model.UserModel;

/**
 * Created by jeanboy on 2017/9/29.
 */
@Database(entities = {UserModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserModelDao userDao();
}
