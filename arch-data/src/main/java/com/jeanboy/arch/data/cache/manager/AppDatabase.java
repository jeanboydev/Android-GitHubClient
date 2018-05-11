package com.jeanboy.arch.data.cache.manager;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jeanboy.arch.data.cache.database.dao.AccessTokenDao;
import com.jeanboy.arch.data.cache.database.dao.UserInfoModelDao;
import com.jeanboy.arch.data.cache.database.dao.UserModelDao;
import com.jeanboy.arch.data.cache.database.model.AccessTokenModel;
import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.cache.database.model.UserModel;

/**
 * Created by jeanboy on 2017/9/29.
 */
@Database(entities = {AccessTokenModel.class, UserInfoModel.class, UserModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserModelDao userDao();

    public abstract AccessTokenDao accessTokenDao();

    public abstract UserInfoModelDao userInfoModelDao();
}
