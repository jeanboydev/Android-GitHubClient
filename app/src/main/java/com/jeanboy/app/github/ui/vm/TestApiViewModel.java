package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.repository.FollowingRepository;
import com.jeanboy.arch.data.repository.UserRepository;

/**
 * Created by 乔晓松 on 2018/5/11 10:53
 */
public class TestApiViewModel extends ViewModel {

    private LiveData<UserInfoModel> userInfo;
    private LiveData<Boolean> checkFollowing;
    private LiveData<Boolean> checkFollowing2;
    private LiveData<Boolean> checkFollowing3;
    private LiveData<Boolean> checkFollowing4;

    private UserRepository userRepository = new UserRepository();
    private FollowingRepository followingRepository = new FollowingRepository();

    public LiveData<UserInfoModel> requestUserInfo(String accessToken, String username) {
        userInfo = userRepository.getUserInfo(accessToken, username);
        return userInfo;
    }

    public LiveData<Boolean> checkFollowing(String accessToken, String username) {
        checkFollowing = followingRepository.checkFollowing(username, accessToken);
        return checkFollowing;
    }

    public LiveData<Boolean> checkFollowing(String accessToken, String username, String targetUsername) {
        checkFollowing2 = followingRepository.checkFollowing(accessToken, username, targetUsername);
        return checkFollowing2;
    }

    public LiveData<Boolean> unfollowUser(String accessToken, String username) {
        checkFollowing3 = followingRepository.unfollowUser(accessToken, username);
        return checkFollowing3;
    }

    public LiveData<Boolean> followUser(String accessToken, String username) {
        checkFollowing4 = followingRepository.followUser(accessToken, username);
        return checkFollowing4;
    }

}
