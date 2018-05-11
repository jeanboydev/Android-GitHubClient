package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.FollowingService;

import retrofit2.Call;

/**
 * Created by 乔晓松 on 2018/5/11 11:32
 */
public class FollowingRepository {

    private AppDatabase database;
    private FollowingService followingService;

    public FollowingRepository() {
        database = DBManager.getInstance().getDatabase();
        followingService = NetManager.getInstance().create(FollowingService.BASE_URL, FollowingService.class);
    }

    /**
     * 不可用 code:401,msg:
     * {"message":"Requires authentication",
     * "documentation_url":"https://developer.github.com/v3/users/followers/#unfollow-a-user"}
     *
     * @param username
     * @param accessToken
     * @return
     */
    public LiveData<Boolean> checkFollowing(String username, String accessToken) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Boolean> call = followingService.checkFollowing(accessToken, username);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Boolean>>() {
                    @Override
                    public void onSuccess(ResponseData<Boolean> response) {
                        Boolean body = response.getBody();
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("checkFollowing", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    public LiveData<Boolean> checkFollowing(String accessToken, String username, String targetUsername) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Boolean> call = followingService.checkFollowing(accessToken, username, targetUsername);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Boolean>>() {
                    @Override
                    public void onSuccess(ResponseData<Boolean> response) {
                        Boolean body = response.getBody();
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("checkFollowing", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    /**
     * 不可用
     *
     * @param accessToken
     * @param username
     * @return
     */
    public LiveData<Boolean> unfollowUser(String accessToken, String username) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Boolean> call = followingService.unfollowUser(accessToken, username);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Boolean>>() {
                    @Override
                    public void onSuccess(ResponseData<Boolean> response) {
                        Boolean body = response.getBody();
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("unfollowUser", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

    /**
     * 不可用
     *
     * @param accessToken
     * @param username
     * @return
     */
    public LiveData<Boolean> followUser(String accessToken, String username) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Call<Boolean> call = followingService.followUser(accessToken, username);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<Boolean>>() {
                    @Override
                    public void onSuccess(ResponseData<Boolean> response) {
                        Boolean body = response.getBody();
                        liveData.setValue(true);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("followUser", "code:" + code + ",msg:" + msg);
                        liveData.setValue(false);
                    }
                });
        return liveData;
    }

}
