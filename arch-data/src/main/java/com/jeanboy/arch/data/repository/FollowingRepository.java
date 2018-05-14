package com.jeanboy.arch.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.cache.manager.AppDatabase;
import com.jeanboy.arch.data.cache.manager.DBManager;
import com.jeanboy.arch.data.net.core.RequestCallback;
import com.jeanboy.arch.data.net.core.RequestParams;
import com.jeanboy.arch.data.net.core.ResponseData;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.net.manager.NetManager;
import com.jeanboy.arch.data.net.service.FollowingService;
import com.jeanboy.arch.data.repository.mapper.UserInfoMapper;

import java.util.List;

import retrofit2.Call;

/**
 * Created by 乔晓松 on 2018/5/11 11:32
 */
public class FollowingRepository {

    private AppDatabase database;
    private FollowingService followingService;

    public FollowingRepository() {
        database = DBManager.getInstance().getDatabase();
        followingService = NetManager.getInstance().createForJSON(FollowingService.BASE_URL, FollowingService.class);
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
        Call<Boolean> call = followingService.checkFollowing("token " + accessToken, username);
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
        Call<Boolean> call = followingService.checkFollowing("token " + accessToken, username, targetUsername);
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

    public LiveData<List<UserInfoModel>> getFollowing(String accessToken, String username, int page) {
        MutableLiveData<List<UserInfoModel>> liveData = new MutableLiveData<>();
        Call<List<UserInfoEntity>> call = followingService.getFollowing(accessToken, username, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<UserInfoEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<UserInfoEntity>> response) {
                        List<UserInfoEntity> body = response.getBody();
                        List<UserInfoModel> userInfoModelList = new UserInfoMapper().transform(body);
                        liveData.setValue(userInfoModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getFollowing", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }

    public LiveData<List<UserInfoModel>> getFollowers(String accessToken, String username, int page) {
        MutableLiveData<List<UserInfoModel>> liveData = new MutableLiveData<>();
        Call<List<UserInfoEntity>> call = followingService.getFollowers(accessToken, username, page);
        NetManager.getInstance().request(new RequestParams<>(call),
                new RequestCallback<ResponseData<List<UserInfoEntity>>>() {
                    @Override
                    public void onSuccess(ResponseData<List<UserInfoEntity>> response) {
                        List<UserInfoEntity> body = response.getBody();
                        List<UserInfoModel> userInfoModelList = new UserInfoMapper().transform(body);
                        liveData.setValue(userInfoModelList);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.d("getFollowers", "code:" + code + ",msg:" + msg);
                        liveData.setValue(null);
                    }
                });
        return liveData;
    }


}
