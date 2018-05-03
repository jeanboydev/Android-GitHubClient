package com.jeanboy.arch.data.cache.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jeanboy.arch.data.cache.database.model.AccessTokenModel;

import java.util.List;

/**
 * Created by jeanboy on 2018/5/2.
 */
@Dao
public interface AccessTokenDao {

    @Query("select * from access_token order by create_time desc limit 0,1")
    LiveData<AccessTokenModel> getLive();

    @Query("select * from access_token order by create_time desc limit 0,1")
    AccessTokenModel get();

    @Query("select * from access_token")
    LiveData<List<AccessTokenModel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AccessTokenModel accessTokenModel);

    @Delete
    void delete(AccessTokenModel accessTokenModel);
}
