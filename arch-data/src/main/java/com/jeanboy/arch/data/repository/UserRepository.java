package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.UserService;
import com.jeanboy.arch.data.repository.handler.MapperHandler;
import com.jeanboy.arch.data.repository.handler.RepositoryHandler;
import com.jeanboy.arch.data.repository.mapper.UserInfoMapper;

import retrofit2.Call;

public class UserRepository {

    private AppDatabase database;
    private UserService userService;

    public UserRepository() {
        database = DBManager.getInstance().getDatabase();
        userService = NetManager.getInstance().create(UserService.BASE_URL, UserService.class);
    }

    public LiveData<UserInfoModel> getUserInfo(long userId, String accessToken) {
        return new RepositoryHandler<UserInfoEntity, UserInfoModel>() {

            @Override
            protected LiveData<UserInfoModel> loadFromRoom() {
                return database.userInfoModelDao().getById(userId);
            }

            @Override
            protected void updateToRoom(UserInfoModel userInfoModel) {
                database.userInfoModelDao().insert(userInfoModel);
            }

            @Override
            protected boolean shouldFetch(@Nullable UserInfoModel userInfoModel) {
                return userInfoModel == null;
            }

            @Override
            protected Call<UserInfoEntity> fetchFromNetwork() {
                if (accessToken == null) return null;
                return userService.getUserInfo("token " + accessToken);
            }

            @Override
            protected MapperHandler<UserInfoEntity, UserInfoModel> onMapper() {
                return new UserInfoMapper();
            }
        }.asLiveData();
    }

    public LiveData<UserInfoModel> getUserInfo(String accessToken, String username) {
        Log.d("getUserInfo param:", "accessToken:" + accessToken + ",username:" + username);
        MutableLiveData<UserInfoModel> liveData = new MutableLiveData<>();
        Call<UserInfoEntity> call = userService.getUserInfo(accessToken, username);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<UserInfoEntity>>() {
                    @Override
                    public void onSuccess(ResponseData<UserInfoEntity> response) {
                        UserInfoEntity body = response.getBody();
                        UserInfoModel userInfoModel = new UserInfoMapper().transform(body);
                        liveData.setValue(userInfoModel);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getUserInfo", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }


}
