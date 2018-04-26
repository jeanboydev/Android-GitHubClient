package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.jeanboy.arch.data.cache.database.model.UserModel;
import com.jeanboy.arch.data.repository.UserRepository;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class UserViewModel extends ViewModel {

    private MediatorLiveData<UserModel> userInfo;

    UserRepository userRepository = new UserRepository();

    public MediatorLiveData<UserModel> getUserInfo(long userId) {
        if (userInfo == null) {
            userInfo = new MediatorLiveData<>();
            loadUserInfo(userId);
        }
        return userInfo;
    }

    private void loadUserInfo(long userId) {
        final LiveData<UserModel> liveData = userRepository.getUserInfo(userId);
        userInfo.addSource(liveData, new Observer<UserModel>() {
            @Override
            public void onChanged(@Nullable UserModel userModel) {
                userInfo.setValue(userModel);
                userInfo.removeSource(liveData);
            }
        });
    }
}
