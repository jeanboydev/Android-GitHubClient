package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.AccessTokenModel;
import com.jeanboy.arch.data.net.entity.AccessTokenEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class AccessTokenMapper extends MapperHandler<AccessTokenEntity, AccessTokenModel> {

    @Override
    public AccessTokenModel transform(AccessTokenEntity accessTokenEntity) {
        AccessTokenModel accessTokenModel = new AccessTokenModel();
        accessTokenModel.setAccessToken(accessTokenEntity.getAccessToken());
        accessTokenModel.setTokenType(accessTokenEntity.getTokenType());
        accessTokenModel.setExpiresIn(7200 * 1000);
        accessTokenModel.setCreateTime(System.currentTimeMillis());
        return accessTokenModel;
    }
}
