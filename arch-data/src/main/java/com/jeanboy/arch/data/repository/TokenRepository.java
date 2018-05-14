package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import com.jeanboy.arch.data.cache.database.model.AccessTokenModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.entity.AccessTokenEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.AuthService;
import com.jeanboy.arch.data.repository.handler.MapperHandler;
import com.jeanboy.arch.data.repository.handler.RepositoryHandler;
import com.jeanboy.arch.data.repository.mapper.AccessTokenMapper;
import com.jeanboy.arch.data.repository.params.TokenParams;

import retrofit2.Call;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class TokenRepository {

    private AppDatabase database;
    private AuthService authService;

    public TokenRepository() {
        database = DBManager.getInstance().getDatabase();
        authService = NetManager.getInstance().createForJSON(AuthService.BASE_URL, AuthService.class);
    }

    public AccessTokenModel getAccessToken(){
        return database.accessTokenDao().get();
    }

    public LiveData<AccessTokenModel> getAccessToken(TokenParams params) {
        return new RepositoryHandler<AccessTokenEntity, AccessTokenModel>() {

            @Override
            protected LiveData<AccessTokenModel> loadFromRoom() {
                return database.accessTokenDao().getLive();
            }

            @Override
            protected void updateToRoom(AccessTokenModel accessTokenModel) {
                database.accessTokenDao().insert(accessTokenModel);
            }

            @Override
            protected boolean shouldFetch(@Nullable AccessTokenModel accessTokenModel) {
                return accessTokenModel == null;
            }

            @Override
            protected Call<AccessTokenEntity> fetchFromNetwork() {
                if (params == null) return null;
                return authService.getAccessToken(params.getClientID(), params.getClientSecret(),
                        params.getCode());
            }

            @Override
            protected MapperHandler<AccessTokenEntity, AccessTokenModel> onMapper() {
                return new AccessTokenMapper();
            }
        }.asLiveData();
    }
}
