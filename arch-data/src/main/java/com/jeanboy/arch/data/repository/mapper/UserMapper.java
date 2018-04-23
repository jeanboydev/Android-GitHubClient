package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.UserModel;
import com.jeanboy.arch.data.net.entity.UserEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

public class UserMapper implements MapperHandler<UserEntity, UserModel> {

    @Override
    public UserModel transform(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setUserName(userEntity.getUsername());
        userModel.setUserNick(userEntity.getUserNick());
        userModel.setCreateTime(System.currentTimeMillis());
        return userModel;
    }
}
