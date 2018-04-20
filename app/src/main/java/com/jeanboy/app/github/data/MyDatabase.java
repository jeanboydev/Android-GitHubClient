package com.jeanboy.app.github.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jeanboy.app.github.data.dao.UserDao;
import com.jeanboy.app.github.data.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
