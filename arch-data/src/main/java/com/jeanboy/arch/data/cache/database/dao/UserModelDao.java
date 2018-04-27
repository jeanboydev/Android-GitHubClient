package com.jeanboy.arch.data.cache.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jeanboy.arch.data.cache.database.model.UserModel;

import java.util.List;


/**
 * Created by jeanboy on 2017/9/29.
 */

@Dao
public interface UserModelDao {

    @Query("select * from user where id = :userId")
    LiveData<UserModel> getById(long userId);

    @Query("select * from user")
    LiveData<List<UserModel>> getAll();

    @Query("select * from user")
    List<UserModel> getAllList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserModel userModel);

    @Update
    void update(UserModel userModel);

    @Delete
    void delete(UserModel userModel);
}
