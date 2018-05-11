package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.arch.data.cache.database.model.AccessTokenModel;
import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.repository.TokenRepository;
import com.jeanboy.arch.data.repository.UserRepository;
import com.jeanboy.arch.data.repository.params.TokenParams;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class AuthViewModel extends ViewModel {

    private LiveData<AccessTokenModel> token;
    private LiveData<UserInfoModel> userInfo;

    private TokenRepository tokenRepository = new TokenRepository();
    private UserRepository userRepository = new UserRepository();

    public LiveData<AccessTokenModel> getToken() {
        if (token == null) {
            token = requestToken(null);
        }
        return token;
    }

    public LiveData<AccessTokenModel> requestToken(TokenParams params) {
        token = tokenRepository.getAccessToken(params);
        return token;
    }


    public LiveData<UserInfoModel> getUserInfo(long userId, String accessToken) {
        if (userInfo == null) {
            userInfo = requestUserInfo(userId, accessToken);
        }
        return userInfo;
    }

    public LiveData<UserInfoModel> requestUserInfo(long userId, String accessToken) {
        userInfo = userRepository.loadUserInfo(userId, accessToken);
        return userInfo;
    }
}
