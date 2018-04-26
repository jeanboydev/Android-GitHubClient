package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import com.jeanboy.arch.data.cache.database.model.UserModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.repository.mapper.UserMapper;
import com.jeanboy.arch.data.net.service.UserService;
import com.jeanboy.arch.data.net.entity.UserEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.repository.handler.RepositoryHandler;

import retrofit2.Call;

public class UserRepository {

    private AppDatabase database;
    private UserService userService;

    public UserRepository() {
        database = DBManager.getInstance().getDatabase();
        userService = NetManager.getInstance().create(UserService.BASE_URL, UserService.class);
    }

    public LiveData<UserModel> getUserInfo(long userId) {
        return new RepositoryHandler<UserEntity, UserModel>(new UserMapper()) {
            @Override
            protected LiveData<UserModel> loadFromRoom() {
                return database.userDao().getById(userId);
            }

            @Override
            protected void updateToRoom(UserModel userModel) {
                database.beginTransaction();
                try {
                    database.userDao().insert(userModel);
                    database.setTransactionSuccessful();
                } finally {
                    database.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable UserModel cache) {
                return cache == null;
            }

            @Override
            protected Call<UserEntity> fetchFromNetwork() {
                return userService.getInfo("","");
            }
        }.asLiveData();
    }
}
