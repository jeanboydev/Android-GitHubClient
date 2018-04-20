package com.jeanboy.app.github.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jeanboy.app.github.data.dao.UserDao;
import com.jeanboy.app.github.data.entity.User;
import com.jeanboy.app.github.net.api.WebApi;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private WebApi webApi;

    private UserDao userDao;

    public LiveData<User> getRemoteUser(String userId) {
        final MutableLiveData<User> data = new MutableLiveData<>();
        webApi.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return data;
    }

    private Map<String, LiveData<User>> userCache = new HashMap<>();//缓存数据

    public LiveData<User> getCacheUser(String userId) {
        LiveData<User> cached = userCache.get(userId);
        if (cached != null) {//有缓存直接返回
            return cached;
        }

        LiveData<User> load = userDao.load(userId);//读取数据库中的数据
        if (load != null) {//数据库有缓存
            userCache.put(userId, load);//存入内存缓存
            return load;
        }

        //没有缓存
        final MutableLiveData<User> data = new MutableLiveData<>();
        userCache.put(userId, data);//创建LiveData放入缓存
        //通过服务器获取
        webApi.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                data.setValue(response.body());//更新数据，LiveData 的 onChange 将会调用
                userDao.save(response.body());//存入数据库
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return data;
    }
}
