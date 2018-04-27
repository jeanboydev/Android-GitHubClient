package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import com.jeanboy.arch.data.cache.database.model.UserModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.cache.manager.DataExecutors;
import com.jeanboy.arch.data.net.entity.UserEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.UserService;
import com.jeanboy.arch.data.repository.handler.RepositoryHandler;
import com.jeanboy.arch.data.repository.mapper.UserMapper;

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
                database.userDao().insert(userModel);
            }

            @Override
            protected boolean shouldFetch(@Nullable UserModel userModel) {
                return userModel == null;
            }

            @Override
            protected Call<UserEntity> fetchFromNetwork() {
                return userService.getInfo("", userId);
            }
        }.asLiveData();
    }

    public void save(UserModel userModel) {
        DataExecutors.getInstance().put(new Runnable() {
            @Override
            public void run() {
                database.userDao().insert(userModel);
            }
        });
    }

    public void update(UserModel userModel) {
        DataExecutors.getInstance().put(new Runnable() {
            @Override
            public void run() {
                database.userDao().update(userModel);
            }
        });
    }
}
