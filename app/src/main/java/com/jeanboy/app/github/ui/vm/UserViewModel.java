package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.arch.data.cache.database.model.UserModel;
import com.jeanboy.arch.data.repository.UserRepository;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class UserViewModel extends ViewModel {

    private LiveData<UserModel> userInfo;

    private UserRepository userRepository = new UserRepository();

    public LiveData<UserModel> getUserInfo(long userId) {
        if (userInfo == null) {
            userInfo = userRepository.getUserInfo(userId);

        }
        return userInfo;
    }

    public void save(UserModel userModel) {
        userRepository.save(userModel);
    }

    public void update(UserModel userModel) {
        userRepository.update(userModel);
    }
}