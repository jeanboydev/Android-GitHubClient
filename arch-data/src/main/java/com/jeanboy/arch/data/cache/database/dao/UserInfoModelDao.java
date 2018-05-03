package com.jeanboy.arch.data.cache.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jeanboy.arch.data.cache.database.model.UserInfoModel;

import java.util.List;


/**
 * Created by jeanboy on 2017/9/29.
 */

@Dao
public interface UserInfoModelDao {

    @Query("select * from user_info where id = :userId")
    LiveData<UserInfoModel> getById(long userId);

    @Query("select * from user_info")
    LiveData<List<UserInfoModel>> getAllLive();

    @Query("select * from user_info")
    List<UserInfoModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserInfoModel userInfoModel);

    @Update
    void update(UserInfoModel userInfoModel);

    @Delete
    void delete(UserInfoModel userInfoModel);
}
