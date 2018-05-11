package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.repository.FollowingRepository;
import com.jeanboy.arch.data.repository.OrganizationRepository;
import com.jeanboy.arch.data.repository.UserRepository;

import java.util.List;

/**
 * Created by 乔晓松 on 2018/5/11 10:53
 */
public class TestApiViewModel extends ViewModel {

    private LiveData<UserInfoModel> userInfo;
    private LiveData<Boolean> checkFollowing;
    private LiveData<Boolean> checkFollowing2;
    private LiveData<Boolean> checkFollowing3;
    private LiveData<Boolean> checkFollowing4;
    private LiveData<List<UserInfoModel>> checkFollowing5;
    private LiveData<List<UserInfoModel>> checkFollowing6;
    private LiveData<List<UserInfoModel>> checkFollowing7;

    private UserRepository userRepository = new UserRepository();
    private FollowingRepository followingRepository = new FollowingRepository();
    private OrganizationRepository organizationRepository = new OrganizationRepository();

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

    public LiveData<List<UserInfoModel>> getFollowing(String accessToken, String username, int page) {
        checkFollowing5 = followingRepository.getFollowing(accessToken, username, page);
        return checkFollowing5;
    }

    public LiveData<List<UserInfoModel>> getFollowers(String accessToken, String username, int page) {
        checkFollowing6 = followingRepository.getFollowers(accessToken, username, page);
        return checkFollowing6;
    }

    public LiveData<List<UserInfoModel>> getOrgMembers(String accessToken, String username, int page) {
        checkFollowing7 = organizationRepository.getOrgMembers(accessToken, username, page);
        return checkFollowing7;
    }

}
