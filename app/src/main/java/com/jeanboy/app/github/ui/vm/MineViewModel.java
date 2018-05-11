package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class MineViewModel extends ViewModel {

    private LiveData<UserInfoModel> userInfo;

    private UserRepository userRepository = new UserRepository();

    @Inject
    public MineViewModel() {
    }

    public LiveData<UserInfoModel> getUserInfo(long userId) {
        if (userInfo == null) {
            userInfo = userRepository.getUserInfo(userId);
        }
        return userInfo;
    }

}