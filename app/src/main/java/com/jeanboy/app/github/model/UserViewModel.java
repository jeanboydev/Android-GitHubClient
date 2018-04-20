package com.jeanboy.app.github.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.app.github.data.entity.User;

import java.util.List;

public class UserViewModel extends ViewModel {

    private String userId;
    private LiveData<User> user;

    public void init(String userId) {
        this.userId = userId;
    }

    public LiveData<User> getUser() {
        return user;
    }


    //缓存一个list列表
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // TODO: 2018/4/18 异步获取
    }
}
