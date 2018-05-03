package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.cache.database.model.UserModel;
import com.jeanboy.arch.data.repository.UserRepository;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class MainViewModel extends ViewModel {

    private LiveData<UserInfoModel> userInfo;

    private UserRepository userRepository = new UserRepository();

    public LiveData<UserInfoModel> getUserInfo(long userId, String accessToken) {
        if (userInfo == null) {
            userInfo = requestUserInfo(userId, accessToken);
        }
        return userInfo;
    }

    public LiveData<UserInfoModel> requestUserInfo(long userId, String accessToken) {
        userInfo = userRepository.getUserInfo(userId, accessToken);
        return userInfo;
    }
}