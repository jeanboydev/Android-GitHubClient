package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.AuthTokenModel;
import com.jeanboy.arch.data.net.entity.AuthTokenEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/6/1 10:35
 */
public class AuthTokenMapper extends MapperHandler<AuthTokenEntity, AuthTokenModel> {

    @Override
    public AuthTokenModel transform(AuthTokenEntity authTokenEntity) {
        AuthTokenModel authTokenModel = new AuthTokenModel();
        authTokenModel.setId(authTokenEntity.getId());
        authTokenModel.setToken(authTokenEntity.getToken());
        authTokenModel.setUrl(authTokenEntity.getUrl());
        authTokenModel.setScopes(authTokenEntity.getScopes());
        authTokenModel.setCreatedAt(authTokenEntity.getCreatedAt());
        authTokenModel.setUpdatedAt(authTokenEntity.getUpdatedAt());
        return authTokenModel;
    }
}
